package pl.edu.pjatk.luftzug.mapper;

import pl.edu.pjatk.luftzug.client.contract.AircraftDto;
import pl.edu.pjatk.luftzug.model.Aircraft;

public class AircraftMapper implements IMapEntities<AircraftDto, Aircraft>{
    @Override
    public Aircraft map(AircraftDto aircraftDto) {
        return map(aircraftDto, new Aircraft());
    }

    @Override
    public Aircraft map(AircraftDto aircraftDto, Aircraft aircraft) {
        aircraft.setCode(aircraftDto.code());
        aircraft.setName(extractName(aircraftDto));
        return aircraft;
    }
}
