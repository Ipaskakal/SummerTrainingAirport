package Controller;

import Controller.Menu.Menu;
import Controller.Menu.MenuEntry;
import Controller.Menu.MyLocale;
//import Model.Enum.ElectricAirplanes;
//import Model.Enum.ICEAirplaneS;
import Exceptoins.InvalidNameException;
import Model.Enum.CargoAirplanes;
import Model.Enum.PassangerAirplanes;
import View.TextConstants;
import View.View;
import Model.Model;
import Model.Entity.Airplane;
import Model.Entity.PassengerAirplane;
import Model.Entity.CargoAirplane;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class Controller {

  private View view;
  private Model model;
  private static Scanner scanner = new Scanner(System.in);
  private static String exitString = "q";



  public Controller(Model model,View view) {
    this.model = model;
    this.view = view;
  }

  public void run() {
    initializeAllAirplanes();
    chooseLocale();
    createMenu();
  }

  private void createMenu() {
    Menu menu = new Menu(view, scanner, TextConstants.MENU);
    menu.addEntry(new MenuEntry(TextConstants.PRINT_All_AIRPLANES_OF_AIRPORT) {
      @Override
      public void run() {
        List<Airplane> airplanes;
        while (true) {
          view.printMessage(TextConstants.ENTER_THE_NAME, TextConstants.OF_AIRPORT);
          String airportName = scanner.next();
          try {
            airplanes = model.getAirplanesOfAirport(airportName);
            break;
          } catch (InvalidNameException e) {
            view.printMessage(e.getMessage());
          }
        }
        for (Airplane airplane : airplanes ) {
          outputAirplane(airplane);
        }
      }
    });
    menu.addEntry(new MenuEntry(TextConstants.CREATE_AIRPORT) {
      @Override
      public void run() {
        createAirport();
      }
    });
    menu.addEntry(new MenuEntry(TextConstants.CALCULATE_AIRPORT_SEATS_AND_CAPACITY) {
      @Override
      public void run() {
        calculateAirportSeatsAndCapacity();
      }
    });
    menu.addEntry(new MenuEntry(TextConstants.SORT_AIRPLANES_IN_AIRPORT_BY_MAX_RANGE_OF_FLIGHT) {
      @Override
      public void run() {
        sortAirportByMaxRangeOfFlight();
      }
    });
    menu.addEntry(new MenuEntry(TextConstants.SEARCH_AIRPLANE_IN_AIRPORT_BY_FUEL_CONSUMPTION) {
      @Override
      public void run() {
        searchAirplaneByFuelConsumption();
      }
    });
    menu.addEntry(new MenuEntry(TextConstants.CHANGE_LOCALE) {
      @Override
      public void run() {
        chooseLocale();
      }
    });
    menu.run();
  }

  private void createAirport() {
    String airportName = "";
    while (true) {
      view.printMessage(TextConstants.ENTER_THE_NAME, TextConstants.OF_NEW_AIRPORT);
      view.printMessage(TextConstants.ENTER, TextConstants.CHAR, exitString,
          TextConstants.TO_EXIT_THE_MENU);
      airportName = scanner.next();
      if (airportName.equals(exitString)) {
        return;
      }
      if (model.isExistAirportWithName(airportName)) {
        view.printMessage(TextConstants.THIS_AIRPORT_ALREADY_EXIST);
        continue;
      }
      break;
    }
    int airportId;
    while (true) {
      view.printMessage(TextConstants.ENTER_THE_ID, TextConstants.OF_NEW_AIRPORT);
      view.printMessage(TextConstants.ENTER, TextConstants.CHAR, exitString,
              TextConstants.TO_EXIT_THE_MENU);
      if(scanner.hasNextInt())
        airportId = scanner.nextInt();
      else if (scanner.next().equals(exitString)) {
        return;
      } else continue;
      if (model.isExistAirportWithId(airportId)) {
        view.printMessage(TextConstants.THIS_AIRPORT_ALREADY_EXIST);
        continue;
      }
      break;
    }
    model.createNewAirport(airportName,airportId);


  }

  private void chooseLocale() {
    final Menu menuLocale = new Menu(view, scanner, TextConstants.CHOOSE_LOCALE);
    for (final Locale locale : MyLocale.getLocaleList()) {
      menuLocale.addEntry(new MenuEntry(locale.getLanguage()) {
        @Override
        public void run() {
          MyLocale.setLocale(locale);
          view.findResourceLocale();
          menuLocale.setExit(true);
        }
      });
    }
    menuLocale.run();
  }

  private void calculateAirportSeatsAndCapacity() {
    final Menu menuStation = new Menu(view, scanner, TextConstants.AVAILABLE_AIRPORTS);
    for (String airportName : model.getAirportsName()) {
      menuStation.addEntry(new MenuEntry(airportName) {
        @Override
        public void run() {
          try {
            printAirplanes(model.getAirportByName(airportName).getAirplanes());
          } catch (Exception e) {};
          view.printMessage(TextConstants.AIRPORT_SITS_NUMBER_AND_CAPACITY, TextConstants.COLON,
                  TextConstants.CAPACITY,TextConstants.DASH,
                  Double.toString(model.airportCapacity(airportName)),TextConstants.TABULATION,
                  TextConstants.SEATS_NUMBER,TextConstants.DASH,Integer.toString(model.airportSeats(airportName)));
          menuStation.setExit(true);
        }
      });
    }
    menuStation.run();
  }

  private void printAirplanes(List<Airplane>  airport) {
    for (Airplane airplane : airport) {
      outputAirplane(airplane);
    }
  }

  private void sortAirportByMaxRangeOfFlight() {
    final Menu menuStation = new Menu(view, scanner, TextConstants.AVAILABLE_AIRPORTS);
    for (String airportName : model.getAirportsName()) {
      menuStation.addEntry(new MenuEntry(airportName)  {
        @Override
        public void run() {
          List<Airplane> airplanesList;
          try {
            airplanesList = model.sortAirportByMaxRangeOfFlight(airportName);
          }catch (InvalidNameException e) {
            return;
          }

          view.printMessage(TextConstants.AIRPLANE_LIST_AFTER_SORTING, TextConstants.COLON);
          printAirplanes(airplanesList);
          menuStation.setExit(true);
        }
      });
    }
    menuStation.run();
  }

  private void searchAirplaneByFuelConsumption() {
    final Menu menuStation = new Menu(view, scanner, TextConstants.AVAILABLE_AIRPORTS);
    for (String airportName : model.getAirportsName()) {
      menuStation.addEntry(new MenuEntry(airportName)  {
        @Override
        public void run() {
          view.printMessage(TextConstants.ENTER_INT_FUEL_CONSUMPTION_RANGE);
          while(!scanner.hasNextInt()) scanner.next();
          int a = scanner.nextInt();
          while(!scanner.hasNextInt()) scanner.next();
          int b = scanner.nextInt();
          List<Airplane> airplaneList = model.AirplaneInAirportWithFuelConsumption(airportName, a, b);
          if (airplaneList.isEmpty()) {
            view.printMessage(TextConstants.THERE_IS_NO_AIRPLANE_IN_THIS_RANGE);
          } else {
            printAirplanes(airplaneList);
          }
          menuStation.setExit(true);
        }
      });
    }
    menuStation.run();
  }


  private void outputAirplane(Airplane airplane) {
    if (airplane.getClass() == PassengerAirplane.class) {
      view.printMessage(TextConstants.TABULATION, TextConstants.PASSENGER_AIRPLANE, TextConstants.COLON);
    } else {
      view.printMessage(TextConstants.TABULATION, TextConstants.CARGO_AIRPLANE, TextConstants.COLON);
    }
    view.printMessage(TextConstants.ID, TextConstants.DASH,
        Integer.toString(airplane.getId()), TextConstants.COMMA, TextConstants.MANUFACTURER,
        TextConstants.DASH,
        airplane.getManufacturer().name(), TextConstants.COMMA, TextConstants.TYPE, TextConstants.DASH,
        airplane.getAirplaneType().getType(), TextConstants.COMMA, TextConstants.MODEL, TextConstants.DASH,
        airplane.getModel(), TextConstants.COMMA, TextConstants.YEAR, TextConstants.DASH,
        Integer.toString(airplane.getYear()), TextConstants.COMMA);
    view.printMessage(TextConstants.PRICE, TextConstants.DASH, Integer.toString(airplane.getPrice()),
        TextConstants.COMMA, TextConstants.MAX_RANGE_OF_FLIGHT, TextConstants.DASH,
        Integer.toString(airplane.getMaxRangeOfFlight()), TextConstants.COMMA, TextConstants.WINGSPAN,
        TextConstants.DASH, Double.toString(airplane.getWingspan()), TextConstants.COMMA,
        TextConstants.FUEL_CONSUMPTION, TextConstants.DASH,
        Double.toString(airplane.getFuelConsumption()), TextConstants.COMMA,
        TextConstants.FUEL_TANK_CAPACITY, TextConstants.DASH, Double.toString(airplane.getFuelTankCapacity()),
        TextConstants.COMMA);

    if (airplane.getClass() == PassengerAirplane.class) {
      PassengerAirplane passengerAirplane = (PassengerAirplane) airplane;
      view.printMessage(TextConstants.MIN_SEAT_NUMBER, TextConstants.DASH,
          Integer.toString(passengerAirplane.getMinSeatsNumber()), TextConstants.COMMA, TextConstants.MAX_SEAT_NUMBER,
          TextConstants.DASH, Integer.toString(passengerAirplane.getMaxSeatsNumber()),TextConstants.NEW_LINE
         );
    } else if (airplane.getClass() == CargoAirplane.class) {
      CargoAirplane cargoAirplane = (CargoAirplane) airplane;
      view.printMessage(TextConstants.MAX_CARGO_WEIGHT, TextConstants.DASH,
          Double.toString(cargoAirplane.getMaxCargoWeight()), TextConstants.NEW_LINE);
    }
  }

  private void initializeAllAirplanes() {
    model.createNewAirport("Passenger",1);
    model.createNewAirport("Cargo", 2);

    for ( PassangerAirplanes airplanes: PassangerAirplanes.values() ){
      PassengerAirplane airplane = PassengerAirplane.builder().id((airplanes.getId())).maxSeatsNumber(airplanes.getMaxSeatsNumber())
              .minSeatsNumber(airplanes.getMinSeatsNumber()).AirplaneManufacturer(airplanes.getManufacturer())
              .AirplaneType(airplanes.getAirplaneType()).fuelTankCapacity(airplanes.getFuelTankCapacity())
              .model(airplanes.getModel()).wingspan(airplanes.getWingspan())
              .price(airplanes.getPrice()).maxRangeOfFlight(airplanes.getMaxRangeOfFlight())
              .year(airplanes.getYear()).fuelConsumption(airplanes.getFuelConsumption()).build();
      model.addAirplaneInAirport(airplane,"Passenger");
    }

    for ( CargoAirplanes airplanes: CargoAirplanes.values() ){
      CargoAirplane airplane;
      airplane= CargoAirplane.builder() .id((airplanes.getId())).maxCargoWeight(airplanes.getMaxCargoWeight())
              .AirplaneManufacturer(airplanes.getManufacturer())
              .AirplaneType(airplanes.getAirplaneType()).fuelTankCapacity(airplanes.getFuelTankCapacity())
              .model(airplanes.getModel()).wingspan(airplanes.getWingspan())
              .price(airplanes.getPrice()).maxRangeOfFlight(airplanes.getMaxRangeOfFlight())
              .year(airplanes.getYear()).fuelConsumption(airplanes.getFuelConsumption()).build();
      model.addAirplaneInAirport(airplane,"Cargo");
    }

  }

}
