package com.java.taskManager.repository;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.enums.TaskStatus;
import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.model.Task;
import com.java.taskManager.request.FilterTaskRequest;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    SequenceDao sequenceDao;


    private static final Logger LOGGER = LoggerFactory.getLogger( TaskDaoImpl.class );

    @Override
    public void addNewTask(Task task) throws Exception{
        try {
            task.setTaskId(sequenceDao.getNextSequenceId("task"));
            task.setTaskCreatedTimestamp(new Date());
            task.setTaskUpdatedTimestamp(new Date());
            task.setTaskStatus(TaskStatus.NEW);
            mongoOperations.save(task);
        } catch(MongoException e) {
            throw new MongoDBException("Failed to fetch documents");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Task> getAllTasks(String emailId,int offset,int size) throws Exception {
        try {
            Criteria criteria = new Criteria().where("email_id").is(emailId)
                    .and("task_status").ne("COMPLETED").and("task_due_date").gte(new Date());

            Aggregation query = aggregate(criteria,offset,size, Sort.Direction.ASC,"task_due_date");
            AggregationResults<Task> task = mongoOperations.aggregate(query,"task_details",Task.class);

            return task.getMappedResults();

        } catch(MongoException exception) {
            LOGGER.error("Failed in getting Document - ",exception);
            throw new MongoDBException("Failed to fetch documents");
        } catch (Exception exception){
            LOGGER.error("Failed in getting Document - ",exception);
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public List<Task> getSpilledTasks(String emailId,int offset,int size) throws Exception{
        try{
            Criteria criteria = new Criteria().where("email_id").is(emailId)
                    .and("task_status").ne("COMPLETED").and("task_due_date").lte(new Date());

            Aggregation query = aggregate(criteria,offset,size, Sort.Direction.ASC,"task_due_date");
            AggregationResults<Task> task = mongoOperations.aggregate(query,"task_details",Task.class);

            return task.getMappedResults();


        }
        catch(MongoException exception) {
            LOGGER.error("Failed in getting Document - ",exception);
            throw new MongoDBException("Failed to fetch documents");
        } catch (Exception exception){
            LOGGER.error("Failed in getting Document - ",exception);
            throw new Exception(exception.getMessage());
        }
    }

    @Override
    public List<Task> getTaskBetweenTimeGivenDueDate(String emailId , Date startDueDate , Date endDueDate) throws Exception{

        try {
            Criteria criteria=new Criteria().andOperator(
                    Criteria.where("email_id").is(emailId),
                    Criteria.where("task_due_date").gte(startDueDate),
                    Criteria.where("task_due_date").lte(endDueDate),
                    Criteria.where("task_status").ne(TaskStatus.COMPLETED));
            Query query = new Query(criteria)
                    .with(Sort.by(Sort.Direction.ASC,"task_due_date"));
            List<Task> taskBetweenGivenDate = mongoOperations.find(query, Task.class, "task_details");
            return taskBetweenGivenDate;
        }
        catch(MongoException exception) {
            LOGGER.error("Failed in getting Document:",exception);
            throw new MongoDBException("Failed to fetch documents");
        } catch (Exception exception){
            LOGGER.error("Failed in getting Document:",exception);
            throw new Exception(exception.getMessage());
        }

    }


    @Override
    public void updateTask(Task task) throws Exception{

        try {
            Query query = new Query(Criteria.where("_id").is(task.getTaskId()));
            Update update = new Update();
            if (task.getTaskName() != null)
                update.set("task_name", task.getTaskName());
            if (task.getTaskLabel() != null)
                update.set("task_label", task.getTaskLabel());
            if (task.getTaskStatus() != null)
                update.set("task_status", task.getTaskStatus());
            if((task.getTaskPriority() != null))
                update.set("task_priority",task.getTaskPriority());
            if (task.getTaskDueDate() != null)
                update.set("task_due_date", task.getTaskDueDate());
            update.set("task_updated_timestamp", new Date());

            mongoOperations.findAndModify(query, update,Task.class);

        }
        catch(MongoException exception) {
            LOGGER.error("Failed in updating Document - ",exception);
            throw new MongoDBException("Failed to update documents");
        } catch (Exception exception){
            LOGGER.error("Failed in updating Document - ",exception);
            throw new Exception(exception.getMessage());
        }

    }

    @Override
    public Task deleteGivenTask(long taskId) throws Exception {


        Task task = null;
        try {
            Query query = new Query(Criteria.where("_id").is(taskId));
            task = mongoOperations.findAndRemove(query, Task.class, "task_details");
        } catch (Exception exception) {
            LOGGER.error("Failed in deleting the document:" + exception);
            throw new MongoDBException("Failed in deleting the document");
        }

        return task;
    }

    @Override
    public List<Task> getFilteredTask(FilterTaskRequest filterTaskRequest, int offset ,int size) throws Exception{

        try {

            Criteria criteria = new Criteria();


            if (!Constants.ALL.equalsIgnoreCase(filterTaskRequest.getTaskStatus()))
                criteria.and("task_status").is(filterTaskRequest.getTaskStatus());

            if (!Constants.ALL.equalsIgnoreCase(filterTaskRequest.getTaskLabels()))
                criteria.and("task_label").is(filterTaskRequest.getTaskLabels());

            if (!Constants.ALL.equalsIgnoreCase(filterTaskRequest.getTaskPriority()))
                criteria.and("task_priority").is(filterTaskRequest.getTaskPriority());

            if (!Constants.ALL.equalsIgnoreCase(filterTaskRequest.getTaskStartDate())) {
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN);
                Date startDate = sdf.parse(filterTaskRequest.getTaskStartDate());
                Date endDate = sdf.parse(filterTaskRequest.getTaskEndDate());
                criteria.and("task_due_date").gte(startDate).lte(endDate);
            }
            criteria.and("email_id").is(filterTaskRequest.getEmailId());


            Aggregation aggregation = aggregate(criteria, offset, size, Sort.Direction.ASC, "task_due_date");


            AggregationResults<Task> task = mongoOperations.aggregate(aggregation, "task_details", Task.class);

            return task.getMappedResults();

        }
        catch (ParseException parseException){
            LOGGER.error("Failed in Parsing the date:"+parseException);
            throw new ParseException(parseException.getMessage(),parseException.getErrorOffset());
        }

        catch (Exception exception){
            LOGGER.error("Failed in Getting mongo data"+exception);
            throw new MongoDBException("Failed in getting Mongo Data");
        }



    }


    private Aggregation aggregate(Criteria criteria, int offset, int size, Sort.Direction direction, String parameter) {

        SortOperation sortOperation;
        MatchOperation matchOperation = new MatchOperation(criteria);
        SkipOperation skipOperation = Aggregation.skip(offset);
        LimitOperation limitOperation = Aggregation.limit(size);
        sortOperation = Aggregation.sort(direction, parameter);

        return Aggregation.newAggregation(matchOperation,sortOperation,skipOperation,limitOperation).
                withOptions(new AggregationOptions(true,false,null));
    }
}
