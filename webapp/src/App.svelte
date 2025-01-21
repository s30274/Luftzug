<script>
  import AddSchedule from "./lib/AddSchedule.svelte";
  import EditSchedule from "./lib/EditSchedule.svelte";
  import { onMount } from 'svelte';

  let schedules = [];
  let showAddSchedule = false;
  let showEditSchedule = false;
  let newSchedule = {
    flightNumber: '',
    duration: '',
    departureAirportCode: '',
    departureDateTime: '',
    arrivalAirportCode: '',
    arrivalDateTime: '',
    airlineCode: '',
    aircraftCode: ''
  };
  let updateId;

  onMount(async () => {
    await refreshData()
  });

  // Get

  const refreshData = async() => {
    const response = await fetch('/api/v1/schedule');
    schedules = await response.json();
  }

  // Add

  const openAddSchedule = () => {
    newSchedule.flightNumber = '';
    newSchedule.duration = '';
    newSchedule.departureAirportCode = '';
    newSchedule.departureDateTime = '';
    newSchedule.arrivalAirportCode = '';
    newSchedule.arrivalDateTime = '';
    newSchedule.airlineCode = '';
    newSchedule.aircraftCode = '';
    showAddSchedule = true;
  }

  const saveSchedule = async() => {
    await fetch(`/api/v1/schedule`, {
      method: 'POST',
      body: JSON.stringify(newSchedule),
      headers: { "Content-Type": "application/json" }
    });
    showAddSchedule = false;
    await refreshData();
  };

  const closeAddSchedule = () => {
    showAddSchedule = false;
  }

  // Edit

  const openEditSchedule = (id) => {
    updateId = id;
    showEditSchedule = true;
  };

  const closeEditSchedule = () => {
    showEditSchedule = false;
  }

  const updateSchedule = async(id) => {
    await fetch(`/api/v1/schedule/${id}`, {
      method: 'PUT',
      body: JSON.stringify(newSchedule),
      headers: { "Content-Type": "application/json" }
    });
    console.log(`Update schedule with id: ${id}`)
    showEditSchedule = false;
    await refreshData();
  }

  // Delete

  const deleteSchedule = async(id) => {
    const confirmed = confirm("Are you sure you want to delete this schedule?");
    if (confirmed) {
      await fetch(`/api/v1/schedule/${id}`, { method: 'DELETE' });
    }
    await refreshData();
  };
</script>

<style>
  table {
    width: 100%;
    border-collapse: collapse;
  }

  th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
  }

  th {
    background-color: #f4f4f4;
  }

  button {
    padding: 5px 10px;
    cursor: pointer;
  }

  button.edit {
    background-color: #4CAF50;
    color: white;
  }

  button.delete {
    background-color: #f44336;
    color: white;
  }
</style>

<h1>List of all schedules</h1>
<button class="add" on:click={() => openAddSchedule()}>Add schedule</button>

<table>
  <thead>
  <tr>
    <th>Flight Number</th>
    <th>Duration</th>
    <th>Departure Airport</th>
    <th>Departure Date</th>
    <th>Arrival Airport</th>
    <th>Arrival Date</th>
    <th>Airline</th>
    <th>Aircraft</th>
    <th>Action</th>
  </tr>
  </thead>
  <tbody>
  {#each schedules as schedule}
    <tr>
      <td>{schedule.flightNumber}</td>
      <td>{schedule.duration}</td>
      <td>{schedule.departureAirportCode}</td>
      <td>{schedule.departureDateTime}</td>
      <td>{schedule.arrivalAirportCode}</td>
      <td>{schedule.arrivalDateTime}</td>
      <td>{schedule.airlineCode}</td>
      <td>{schedule.aircraftCode}</td>
      <td>
        <button class="edit" on:click={() => openEditSchedule(schedule.id)}>Edit</button>
        <button class="delete" on:click={() => deleteSchedule(schedule.id)}>Delete</button>
      </td>
    </tr>
  {/each}
  </tbody>
</table>

<AddSchedule
        showModal={showAddSchedule}
        newSchedule={newSchedule}
        saveSchedule={saveSchedule}
        closeAddSchedule={closeAddSchedule}
/>

<EditSchedule
        showModal={showEditSchedule}
        scheduleId={updateId}
        newSchedule={newSchedule}
        updateSchedule={updateSchedule}
        closeEditSchedule={closeEditSchedule}
/>