package net.javaguides.springboot.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repo;

    public List<Properties> listAll(){
        return (List<Properties>) repo.findAll();
    }

    public void save(Properties properties) {
        repo.save(properties);
    }

    public Properties get(Integer id){
        Optional<Properties> result=repo.findById(id);
        Properties properties=null;
        if(result.isPresent()){
            properties=result.get();
        }
        else{
            throw new RuntimeException("Properties not foound for id " + id);
        }
        return properties;
    }

    public void delete(Integer id) throws UserPrincipalNotFoundException {
       // Long count=repo.countById(id);
        //if(count==null || count==0){
        //    throw new UserPrincipalNotFoundException("Could not find Property with ID "+ id);
        //}
        repo.deleteById(id);
    }

}
