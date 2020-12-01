package service;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repository.CustomerRepository;
import repository.ProvinceRepository;

public class ProvinceServiceImpl implements ProvinceService{
    @Autowired
    private ProvinceRepository provinceRepository;

//    @Override
//    public Iterable<Province> findAll() {
//        return provinceRepository.findAll();
//    }

    @Override
    public Page<Province> findAll(Pageable pageable) {
        return provinceRepository.findAll(pageable);
    }

    @Override
    public Province finById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.delete(id);
    }

    @Override
    public Page<Province> findAllByNameContaining(String name, Pageable pageable) {
        return provinceRepository.findAllByNameContaining(name,pageable);
    }


}
