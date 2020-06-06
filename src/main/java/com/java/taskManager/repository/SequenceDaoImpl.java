package com.java.taskManager.repository;

import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.model.SequenceId;
import org.springframework.stereotype.Repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class SequenceDaoImpl implements SequenceDao {

    @Autowired
    private MongoOperations mongoOperation;

    @Override
    public long getNextSequenceId(String key) throws MongoDBException {

        Query query = new Query(Criteria.where("_id").is(key));

        //increase sequence id by 1
        Update update = new Update();
        update.inc("seq", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        SequenceId seqId =
                mongoOperation.findAndModify(query, update, options, SequenceId.class);


        if (seqId == null) {
            throw new MongoDBException("Unable to get sequence id for key : " + key);
        }

        return seqId.getSeq();

    }

}