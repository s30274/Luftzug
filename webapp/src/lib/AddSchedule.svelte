<script>
    export let showModal = false;
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
    export let saveSchedule;
    export let closeAddSchedule;

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
    <div class="modal">
        <div class="modal-content">
            <span class="close" on:click={closeAddSchedule}>&times;</span>
            <h2>Add Schedule</h2>
            <form on:submit|preventDefault={saveSchedule}>
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
                <button type="submit">Save Schedule</button>
            </form>
        </div>
    </div>
{/if}