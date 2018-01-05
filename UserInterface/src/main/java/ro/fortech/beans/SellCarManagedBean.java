package ro.fortech.beans;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import ro.fortech.services.CarService;
import ro.fortech.services.DealerService;
import utilities.dtos.CarDto;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class SellCarManagedBean implements Serializable {

    private LazyDataModel<CarDto> carModel;
    @EJB
    private CarService carService;
    @EJB
    private DealerService dealerService;
    @Inject
    private UserManagedBean userManagedBean;

    public String doSell(String name, double carPrice) {
        carService.validateSale(name);
        dealerService.validateSale(userManagedBean.getUser(), carPrice);
        return "sellCar?faces-redirect=true";
    }

    @PostConstruct
    void init() {
        carModel = new LazyDataModel<CarDto>() {
            @Override
            public List<CarDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<CarDto> carDtoList = carService.getCarList(first, pageSize, sortField, sortOrder, filters);
                carModel.setRowCount(carService.getCarTotalCount());
                return carDtoList;
            }
        };
    }

    public LazyDataModel<CarDto> getCarModel() {
        return carModel;
    }

    public void setCarModel(LazyDataModel<CarDto> carModel) {
        this.carModel = carModel;
    }
}
