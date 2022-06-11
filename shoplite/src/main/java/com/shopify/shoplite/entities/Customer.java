package com.shopify.shoplite.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

import com.mapbox.geojson.Point;
import com.mapbox.api.geocoding.v5.MapboxGeocoding;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.api.geocoding.v5.models.GeocodingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "The customer name may not be empty!")
    @Size(max = 64, message = "The customer name should be less than 64 chars!")
    private String name;

    @NotEmpty(message = "The address may not be empty! ")
    @Pattern(regexp = "[\\n|\\s|\\S]*([A-PR-UWYZ](([0-9](([0-9]|[A-HJKSTUW])?)?)|([A-HK-Y][0-9]([0-9]|[ABEHMNPRVWXY])?)) ?[0-9][ABD-HJLNP-UW-Z]{2})$", message = "Must include a valid UK postcode at the end of the address!")
    private String address;

    private Double latitude;

    private Double longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void setLongtitudeLatitude() {
        MapboxGeocoding mapboxGeocoding = MapboxGeocoding.builder()
                .accessToken("pk.eyJ1IjoiYXppei1zZXJpbiIsImEiOiJjbDFjOXlmd28wNW9kM2JwNHV4cWN6eXdxIn0.kR-B2eXCwwf8KngAHCQjSw")
                .query(this.address)
                .build();
        try {
            // number of milliseconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mapboxGeocoding.enqueueCall(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {

                List<CarmenFeature> results = response.body().features();
                // Log the first results Point.
                Point firstResultPoint = results.get(0).center();
                setLongitude(firstResultPoint.longitude());
                setLatitude(firstResultPoint.latitude());
                System.out.println("onResponse: " + firstResultPoint.toString());


            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
