package czt_ssm_service;

import czt_ssm_domain.Product;

import java.util.List;

public interface IproductService {

    public List<Product> findAll(Integer page,Integer size) throws Exception;

    void save(Product product) throws Exception;
}
