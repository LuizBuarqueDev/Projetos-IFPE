package br.com.ifpe.oficina.services.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import br.com.ifpe.oficina.entities.abstractclasses.Car;
import br.com.ifpe.oficina.entities.concreteclasses.Client;
import br.com.ifpe.oficina.entities.concreteclasses.CombustionCar;
import br.com.ifpe.oficina.entities.concreteclasses.EletricCar;
import br.com.ifpe.oficina.interfaces.IController;
import br.com.ifpe.oficina.persistence.GenericDAO;
import br.com.ifpe.oficina.services.factories.DAOFactory;

public class CarController extends AbstractController<Car> implements IController<Car>{

    private static final CarController instance = new CarController();

    GenericDAO<Car> carDAO = DAOFactory.createDAO(Car.class);

    private CarController() {
    }

    public static CarController getInstance() {
        return instance;
    }

    private Car searchCar(String plate) {
        Predicate<Car> filterByCar = car -> car.getPlate().equals(plate);
        return carDAO.read(filterByCar);
    }

    protected void validateInsert(Car car) {
        if (searchCar(car.getPlate()) != null) {
            throw new RuntimeException("O carro já existe");
        }
    }

    public void create(String type, String plate, String traction) {
        Car car = searchCar(plate);
        if (car != null) {
            throw new NoSuchElementException("A placa '" + plate + "'já existe");
        }
        if (type.equals("1")) {
            car = new CombustionCar();
            car.setClient(null);
            car.setPlate(plate);
            car.setTraction(traction);

        } else if (type.equals("2")) {
            car = new EletricCar();
            car.setClient(null);
            car.setPlate(plate);
            car.setTraction(traction);

        } else {
            throw new IllegalArgumentException("Tipo de carro invalido");
        }

        car.setClient(new Client.ClientBuilder().build());

        carDAO.create(car);
    }

    @Override
    public Car read(String plate) {
        Car car = searchCar(plate);
        if (car == null) {
            throw new NoSuchElementException("A placa '" + plate + "' não foi encontrada");
        }
        return car;
    }

    public void update(String plate, String traction) {
        Car car = searchCar(plate);
        if (car instanceof Car) {
            throw new NoSuchElementException("A placa '" + plate + "' não foi encontrada");
        }
        try {
            Car carCopy = (Car) car.clone();
            carCopy.setPlate(plate);
            carCopy.setPlate(traction);
            carCopy.setTraction(traction);

            carDAO.update(viewAll().indexOf(car), carCopy);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String plate) {
        Car car = searchCar(plate);
        if (car == null) {
            throw new NoSuchElementException("A placa '" + plate + "' não foi encontrada");
        }
        carDAO.delete(car);
    }

    @Override
    public List<Car> viewAll() {
        return carDAO.viewAll();
    }
}
