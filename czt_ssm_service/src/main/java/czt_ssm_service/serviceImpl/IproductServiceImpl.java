package czt_ssm_service.serviceImpl;

import com.github.pagehelper.PageHelper;
import czt_ssm_dao.IproductDao;
import czt_ssm_domain.Product;
import czt_ssm_service.IproductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class IproductServiceImpl implements IproductService {


    /**
     * service 层
     */
    @Autowired
    private IproductDao dao;
    @Override
    public List<Product> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return dao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        dao.save(product);
    }


}
