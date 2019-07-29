package Model;

import Exceptoins.InvalidNameException;
import Model.Entity.Airplane;
import Model.Entity.Airport;
import Model.Entity.CargoAirplane;
import Model.Entity.PassengerAirplane;

import java.util.*;

public class Model {
    private List<Airport> airports = new ArrayList<>();

    public Airport getAirportByName(String name) throws InvalidNameException {
        for (Airport airport: airports) {
            if (airport.getName().equals(name)) return airport;
        }
        throw new InvalidNameException("Invalid name of airport",name);
    }

    public void addAirplaneInAirport(Airplane airplane,String airportName){
        Airport airport;
        try{
            airport= getAirportByName(airportName);
        }catch (InvalidNameException e){
            return;
        };
        airport.addAirplane(airplane);
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public double airportCapacity(String airportName){
        Airport airport;
        try {
             airport = this.getAirportByName(airportName);
        } catch (Exception e) {
            return -1;
        }
        double res=0.0;
        for (Airplane airplane: airport.getAirplanes())
            if (airplane.getClass() == CargoAirplane.class)
            {
                CargoAirplane cargoAirplane= (CargoAirplane) airplane;
                res+=cargoAirplane.getMaxCargoWeight();
            }
        return res;
    }

    public int airportSeats(String airportName){
        Airport airport;
        try {
            airport = this.getAirportByName(airportName);
        } catch (Exception e) {
            return -1;
        }
        int res=0;
        for (Airplane airplane: airport.getAirplanes())
            if (airplane.getClass() == PassengerAirplane.class)
            {
                PassengerAirplane passengerAirplane= (PassengerAirplane) airplane;
                res+=passengerAirplane.getMaxSeatsNumber();
            }
        return res;
    }

    public List<String> getAirportsName(){
        List<String> names=new ArrayList<String>();
        for(Airport airport: airports){
            names.add(airport.getName());
        }
        return names;
    }

    public List<Airplane> getAirplanesOfAirport(String airportName) throws InvalidNameException {
         return getAirportByName(airportName).getAirplanes();
    }

    public boolean isExistAirportWithName(String airportName) {
        for (Airport airport: airports) {
            if (airport.getName().equals(airportName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistAirportWithId(int id) {
        for (Airport airport : airports) {
            if (airport.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void createNewAirport(String airportName, int airportId){
        Airport airport = Airport.builder().name(airportName)
                .id(airportId).build();
        airports.add(airport);
    }

    public List<Airplane> sortAirportByMaxRangeOfFlight(String airportName) throws InvalidNameException {
        Airport airport;
        try {
            airport = this.getAirportByName(airportName);
        } catch (Exception e) {
            throw new InvalidNameException("Invalid name of airport",airportName);
        }
        List<Airplane> airplanesList = new ArrayList<>();
        for(Airplane airplane: airport.getAirplanes())
            airplanesList.add(airplane);

        int i = airplanesList.size();
        int minRangeIndex = i-1;
        for(int k=0;k<i;k++) {
            minRangeIndex = i-1;
            for (int j =k; j <i; j++) {
                if(airplanesList.get(j).getMaxRangeOfFlight()<airplanesList.get(minRangeIndex).getMaxRangeOfFlight())
                    minRangeIndex=j;
            }
            Airplane airplane = airplanesList.get(minRangeIndex);
            airplanesList.set(minRangeIndex,airplanesList.get(k));
            airplanesList.set(k,airplane);

        }
        return airplanesList;
    }

    public List<Airplane> AirplaneInAirportWithFuelConsumption(String airportName, int low, int high) {
        Airport airport;
        try {
             airport = this.getAirportByName(airportName);
        } catch (InvalidNameException e) {
            return null;
        }
        if (high < low) {
            int temp = high;
            high = low;
            low = temp;
        }
        List<Airplane> airplaneList = new ArrayList<>();
        for (Airplane airplane : airport.getAirplanes()) {
            if (airplane.getFuelConsumption() >= low && airplane.getFuelConsumption() <= high) {
                airplaneList.add(airplane);
            }
        }
        return airplaneList;
    }


}
