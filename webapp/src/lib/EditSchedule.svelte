<script>
    import {onMount} from "svelte";

    export let showModal = false;
    export let scheduleJson;
    export let newSchedule = {
        flightNumber: '',
        duration: '',
        departureAirportCode: '',
        departureDateTime: '',
        arrivalAirportCode: '',
        arrivalDateTime: '',
        airlineCode: '',
        aircraftCode: ''
    };
    export let scheduleId;
    export let updateSchedule;
    export let closeEditSchedule;

    onMount(async () => {
        await refreshData()
    });

    const refreshData = async() => {
        const response = await fetch(`/api/v1/schedule/${scheduleId}`);
        scheduleJson = await response.json();
        newSchedule.flightNumber = scheduleJson.flightNumber;
        newSchedule.duration = scheduleJson.duration;
        newSchedule.departureAirportCode = scheduleJson.departureAirportCode;
        newSchedule.departureDateTime = scheduleJson.departureDateTime;
        newSchedule.arrivalAirportCode = scheduleJson.arrivalAirportCode;
        newSchedule.arrivalDateTime = scheduleJson.arrivalDateTime;
        newSchedule.airlineCode = scheduleJson.airlineCode;
        newSchedule.aircraftCode = scheduleJson.aircraftCode;
    }
</script>

<style>
    .modal {
        display: block;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.4);
        padding-top: 60px;
    }

    .modal-content {
        background-color: #fefefe;
        margin: 5% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
    }

    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }

    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>

{#if showModal}
    {refreshData()}
    <div class="modal">
        <div class="modal-content">
            <span class="close" on:click={closeEditSchedule}>&times;</span>
            <h2>Edit Schedule</h2>
            <form on:submit|preventDefault={() => updateSchedule(scheduleId)}>
                <label>Flight Number:</label>
                <input type="text" bind:value={newSchedule.flightNumber} required /><br><br>
                <label>Duration:</label>
                <input type="text" bind:value={newSchedule.duration} required /><br><br>
                <label>Departure Airport:</label>
                <input type="text" bind:value={newSchedule.departureAirportCode} required /><br><br>
                <label>Departure Date:</label>
                <input type="datetime-local" bind:value={newSchedule.departureDateTime} required /><br><br>
                <label>Arrival Airport:</label>
                <input type="text" bind:value={newSchedule.arrivalAirportCode} required /><br><br>
                <label>Arrival Date:</label>
                <input type="datetime-local" bind:value={newSchedule.arrivalDateTime} required /><br><br>
                <label>Airline:</label>
                <input type="text" bind:value={newSchedule.airlineCode} required /><br><br>
                <label>Aircraft:</label>
                <input type="text" bind:value={newSchedule.aircraftCode} required /><br><br>
                <button type="submit">Update Schedule</button>
            </form>
        </div>
    </div>
{/if}