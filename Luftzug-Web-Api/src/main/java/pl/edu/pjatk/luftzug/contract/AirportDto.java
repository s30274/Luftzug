package pl.edu.pjatk.luftzug.contract;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportDto {
    private long id;
    private String code;
    private String name;
    private double latitude;
    private double longitude;
    private String countryCode;
}
