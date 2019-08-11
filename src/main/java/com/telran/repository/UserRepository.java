package com.telran.repository;

import com.telran.controller.SearchDto;
import com.telran.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//Jpa           -> JpaRepository
//Mongo         -> MongoRepository
//ElasticSearch -> ElasticsearchRepository
//Cassandra     -> CassandraRepository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

    User findByEmail(String email);
    List<User> findAllByName(String name);
}


interface UserRepositoryCustom {
    List<User> searchUsers(SearchDto searchDto);
}

@Repository
class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> searchUsers(SearchDto searchDto) {
        System.out.println("Mongo Template search");
        Criteria baseCriteria = new Criteria();

        List<Criteria> criterias = new ArrayList<>();

        if (searchDto.getName() != null) {
            criterias.add(Criteria.where("name").is(searchDto.getName()));
        }

        if (searchDto.getColor() != null) {
            //embedded (see DBref below to compare querying)
            criterias.add(Criteria.where("userDetails.favouriteColor").is(searchDto.getColor()));
        }

        //Since UserPhoneNumber is a separate collection, it is necessary:
        //1. to find UserPhoneNumber by phone number
        //2. compare user.userPhoneNumber.$id with userPhoneNumber results


//        if (searchDto.getPhoneNumber() != null) {
//            criterias.add(Criteria.where("userPhoneNumber.phoneNumber").is(searchDto.getPhoneNumber()));
//        }

        Query query = new Query();
        query.addCriteria(baseCriteria.andOperator(criterias.toArray(new Criteria[criterias.size()])));
        query.fields().exclude("email");
        query.fields().exclude("userDetails");


        List<User> users = mongoTemplate.find(
                query,
                User.class
        );


        return users;
    }
}

