<script>
    let airports = [];
    let airlines = [];
    let aircrafts = [];
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

    const refreshData = async() => {
        const responseAirport = await fetch('/api/v1/reference/airport');
        airports = await responseAirport.json();

        const responseAirline = await fetch('/api/v1/reference/airline');
        airlines = await responseAirline.json();

        const responseAircraft = await fetch('/api/v1/reference/aircraft');
        aircrafts = await responseAircraft.json();
    }

</script>

<style>
    .modal-backdrop {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        display: flex;
        justify-content: center;
        align-items: center;
        z-index: 1000;
    }

    .modal {
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        width: 90%;
        max-width: 500px;
        max-height: 90vh;
        overflow-y: auto;
        padding: 2rem;
    }

    h2 {
        color: #2c3e50;
        margin-bottom: 1.5rem;
        font-size: 1.5rem;
    }

    form {
        display: grid;
        gap: 0.5rem;
    }

    label {
        font-weight: bold;
        color: #34495e;
        display: block;
        margin-bottom: 0.5rem;
    }

    input, select {
        width: 70%;
        padding: 0.5rem;
        border: 1px solid #bdc3c7;
        border-radius: 4px;
        font-size: 1rem;
    }

    input:focus, select:focus {
        outline: none;
        border-color: #3498db;
        box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2);
    }

    button {
        background-color: #3498db;
        color: white;
        border: none;
        padding: 0.75rem 1rem;
        border-radius: 4px;
        cursor: pointer;
        font-size: 1rem;
        transition: background-color 0.3s ease;
    }

    button:hover {
        background-color: #2980b9;
        display: flex;
    }
</style>

{#if showModal}
    {refreshData()}
    <div class="modal-backdrop" on:click|self={closeAddSchedule}>
        <div class="modal">
            <button class="close" on:click={closeAddSchedule} aria-label="Close modal">&times;</button>
            <h2>Add Schedule</h2>
            <form on:submit|preventDefault={saveSchedule}>
                <label>Flight Number:</label>
                <input type="text" bind:value={newSchedule.flightNumber} required /><br><br>
                <label>Duration:</label>
                <input type="text" bind:value={newSchedule.duration} required /><br><br>
                <label>Departure Airport:</label>
                <select bind:value={newSchedule.departureAirportCode} required >
                    {#each airports as airport}
                        <option value="{airport.code}">{airport.code}</option>
                    {/each}
                </select><br/><br/>
                <label>Departure Date:</label>
                <input type="datetime-local" bind:value={newSchedule.departureDateTime} required /><br><br>
                <label>Arrival Airport:</label>
                <select bind:value={newSchedule.arrivalAirportCode} required >
                    {#each airports as airport}
                        <option value="{airport.code}">{airport.code}</option>
                    {/each}
                </select><br><br>
                <label>Arrival Date:</label>
                <input type="datetime-local" bind:value={newSchedule.arrivalDateTime} required /><br><br>
                <label>Airline:</label>
                <select bind:value={newSchedule.airlineCode} required >
                    {#each airlines as airline}
                        <option value="{airline.code}">{airline.code}</option>
                    {/each}
                </select><br><br>
                <label>Aircraft:</label>
                <select bind:value={newSchedule.aircraftCode} required >
                    {#each aircrafts as aircraft}
                        <option value="{aircraft.code}">{aircraft.code}</option>
                    {/each}
                </select><br><br>
                <button type="submit">Save Schedule</button>
            </form>
        </div>
    </div>
{/if}