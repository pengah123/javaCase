package Service;

import Domain.Province;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ProvinceService {
    public List<Province> findAll();

    public String findAllJson() throws JsonProcessingException;
}
