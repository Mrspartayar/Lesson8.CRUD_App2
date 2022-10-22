package yar.lavr.dao;

import org.springframework.stereotype.Component;
import yar.lavr.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
// All values for connect with db in variables
    private static final String URL = "jdbc:postgresql://localhost:5500/postgres";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user";
    // Create connection with jdbc to db
    private static Connection connection;
// Static block ini
    static {
        try {
            // Call "forName" and loading jdbc driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // In connection put answer from driver
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Person> index() {
        List<Person> people = new ArrayList<Person>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            // In ResultSet we put our result
            // In statement.executeQuery we do SQL request
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }

    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    return null;
    }

    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
            //INSERT INTO PERSON VALUES (1, 'Tom', 18, 'asdas@dsfasd.com')
            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Get person from form edit
    public void update(int id, Person updatedPerson) {
        //Find person for his id
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id)
{
//        people.removeIf(p -> p.getId() == id);
//    }
 }
}
