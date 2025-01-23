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
  let searchTerm = '';
  let searchCategory = 'flightNumber';

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

  // Download

  const downloadSchedulePdf = (id) => {
    window.open(`/api/v1/schedule/pdf/${id}`, '_blank').focus();
  }

  // Search

  $: filteredSchedules = schedules.filter(schedule =>
          schedule[searchCategory].toLowerCase().includes(searchTerm.toLowerCase())
  );

</script>

<style>
  :global(body) {
    font-family: Arial, sans-serif;
    line-height: 1.6;
    color: #333;
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f4f4f4;
  }

  h1 {
    color: #2c3e50;
    text-align: center;
    margin-bottom: 30px;
  }

  .controls {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
  }

  .search-container {
    display: flex;
    gap: 10px;
  }

  input, select {
    padding: 8px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }

  table {
    width: 100%;
    border-collapse: separate;
    border-spacing: 0;
    background-color: white;
    box-shadow: 0 1px 3px rgba(0,0,0,0.2);
    border-radius: 8px;
    overflow: hidden;
  }

  th, td {
    padding: 12px 15px;
    text-align: left;
  }

  th {
    background-color: #3498db;
    color: white;
    font-weight: bold;
    text-transform: uppercase;
    font-size: 0.9em;
  }

  tr:nth-child(even) {
    background-color: #f8f8f8;
  }

  button {
    padding: 8px 12px;
    cursor: pointer;
    border: none;
    border-radius: 4px;
    font-weight: bold;
    transition: background-color 0.3s ease;
  }

  button.add {
    background-color: #2ecc71;
    color: white;
  }

  button.add:hover {
    background-color: #27ae60;
  }

  button.edit {
    background-color: #3498db;
    color: white;
  }

  button.edit:hover {
    background-color: #2980b9;
  }

  button.delete {
    background-color: #e74c3c;
    color: white;
  }

  button.delete:hover {
    background-color: #c0392b;
  }

  .action-buttons {
    display: flex;
    gap: 5px;
  }
</style>

<h1>Luftzug</h1>

<div class="controls">
  <button class="add" on:click={openAddSchedule}>Add Schedule</button>
  <div class="search-container">
    <select bind:value={searchCategory}>
      <option value="flightNumber">Flight Number</option>
      <option value="departureAirportCode">Departure Airport</option>
      <option value="arrivalAirportCode">Arrival Airport</option>
      <option value="airlineCode">Airline</option>
      <option value="aircraftCode">Aircraft</option>
    </select>
    <input type="text" placeholder="Search..." bind:value={searchTerm}>
  </div>
</div>

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
  {#each filteredSchedules as schedule}
    <tr>
      <td>{schedule.flightNumber}</td>
      <td>{schedule.duration}</td>
      <td>{schedule.departureAirportCode}</td>
      <td>{schedule.departureDateTime}</td>
      <td>{schedule.arrivalAirportCode}</td>
      <td>{schedule.arrivalDateTime}</td>
      <td>{schedule.airlineCode}</td>
      <td>{schedule.aircraftCode}</td>
      <td class="action-buttons">
        <button class="edit" on:click={() => openEditSchedule(schedule.id)}>Edit</button>
        <button class="delete" on:click={() => deleteSchedule(schedule.id)}>Delete</button>
        <button class="pdf" on:click={() => downloadSchedulePdf(schedule.id)}>Pdf</button>
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