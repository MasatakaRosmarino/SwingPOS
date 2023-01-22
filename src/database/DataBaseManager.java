package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Contact;
import model.Customer;
import model.Gender;
import model.Invoice;
import model.Product;
import model.ProductCategory;
import model.ProductCondition;
import model.Supplier;
import utilities.Utilities;

public class DataBaseManager {

    public static Connection connection;

    public static final int MAX_LIMIT = 100;

    public static int limit = MAX_LIMIT;
    public static int customerOffset = 0;
    public static int supplierOffset = 0;
    public static int productOffset = 0;

    public static String dbUrl;
    public static String dbPort;
    public static String dbUser;
    public static String dbPassword;

    public void connectToDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Utilities.notifyOfError(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(("jdbc:mysql://" + dbUrl), dbUser, dbPassword);
        } catch (SQLException e) {
            Utilities.notifyOfError(e.getMessage());
        }
    }

    public static void disconnectFromDB() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                Utilities.notifyOfError(e.getMessage());
            }
        }
    }

    public static void resetCustomerOffset() {
        customerOffset = 0;
    }

    public static void resetSupplierOffset() {
        supplierOffset = 0;
    }

    public static void resetProductOffset() {
        productOffset = 0;
    }

    public Connection getConnection() {
        return connection;
    }

    /************************* CUSTOMER **********************/
    public boolean customerRowExists(int id) {
        int count = 0;

        try {
            String checkSql = "SELECT count(*) as count from customer where customer_id=?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);

            checkStmt.setInt(1, id);
            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            count = checkResult.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count > 0;
    }

    public int getCustomerCount() {
        String query = "SELECT COUNT(*) AS customer_count FROM customer";

        Statement stmt;

        try {
            stmt = connection.createStatement();
            ResultSet resultsSet = stmt.executeQuery(query);
            resultsSet.next();
            int count = resultsSet.getInt("customer_count");
            resultsSet.close();

            return count;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public List<Customer> fetchCustomersListFromDatabase(int limit, int offset) {
        List<Customer> customersList = new ArrayList<>();
        Customer customer;

        String query = "SELECT * FROM customer ORDER BY creation_date DESC LIMIT " + limit + " OFFSET " + offset;

        try {
            Statement readStmt = connection.createStatement();

            ResultSet resultsList = readStmt.executeQuery(query);

            // load the whole list
            while (resultsList.next()) {
                int customerId = resultsList.getInt("customer_id");
                String name = resultsList.getString("name");
                String lastname = resultsList.getString("lastname");
                Gender gender = Enum.valueOf(Gender.class, resultsList.getString("gender"));
                String idNumber = resultsList.getString("id_number");
                LocalDate birthdate = LocalDate.parse(resultsList.getString("birthdate"));

                customer = new Customer(customerId, name, lastname, gender, idNumber, birthdate);

                customersList.add(customer);
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return customersList;
    }

    public List<Customer> fetchFilteredCustomersListFromDatabase(String filter) {
        List<Customer> customersList = new ArrayList<>();

        String query = "SELECT customer.* FROM shop_user_contact INNER JOIN customer ON shop_user_contact.customer_id = customer.customer_id \n"
                + "INNER JOIN contact ON shop_user_contact.contact_id = contact.contact_id \n"
                + "WHERE customer.customer_id LIKE '%" + filter + "%' OR customer.name LIKE '%" + filter
                + "%' OR customer.lastname LIKE '%" + filter + "%' \n" + "OR customer.id_number LIKE '%" + filter
                + "%' OR contact.phone LIKE '%" + filter + "%' OR contact.email LIKE '%" + filter + "%' \n"
                + " OR contact.address LIKE '%" + filter + "%' OR contact.zip_code LIKE '%" + filter
                + "%' OR contact.city LIKE '%" + filter + "%' \n" + " OR contact.province LIKE '%" + filter
                + "%' OR contact.country LIKE '%" + filter + "%' ORDER BY customer.creation_date DESC";

        Statement readStmt;

        try {
            readStmt = connection.createStatement();

            ResultSet resultsList = readStmt.executeQuery(query);

            // load the whole list
            while (resultsList.next()) {
                int customerId = Integer.valueOf(resultsList.getInt("customer_id"));
                String name = resultsList.getString("name");
                String lastname = resultsList.getString("lastname");
                Gender gender = Enum.valueOf(Gender.class, resultsList.getString("gender"));
                String idNumber = resultsList.getString("id_number");
                LocalDate birthdate = LocalDate.parse(resultsList.getString("birthdate"));

                Customer customer = new Customer(customerId, name, lastname, gender, idNumber, birthdate);

                customersList.add(customer);
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return customersList;
    }

    public Customer fetchCustomerFromTableRow(int id) {
        Customer customer;
        String query = "SELECT * FROM customer WHERE customer_id = ?";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, id);

            ResultSet resultsList = readStmt.executeQuery();

            int customerId = id;
            String customerName;
            String customerLastName;
            Gender customerGender;
            String customerIdNumber;
            LocalDate customerBirthDate;

            while (resultsList.next()) {
                customerName = resultsList.getString("name");
                customerLastName = resultsList.getString("lastname");
                customerGender = Gender.valueOf(resultsList.getString("gender"));
                customerIdNumber = resultsList.getString("id_number");
                customerBirthDate = LocalDate.parse(resultsList.getString("birthdate"));

                customer = new Customer(customerId, customerName, customerLastName, customerGender, customerIdNumber,
                        customerBirthDate);

                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getHighestCustomerId() {
        String query = "SELECT customer_id FROM customer WHERE customer_id = (SELECT MAX(customer_id) FROM customer)";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                int id = Integer.valueOf(resultsList.getString(1));

                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void saveCustomer(Customer customer) {
        try {
            String insertSql = "INSERT INTO customer (name, lastname, gender, id_number, birthdate) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);

            String updateSql = "UPDATE customer SET name = ?, lastname = ?, gender = ?, id_number = ?, birthdate = ? WHERE customer_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);

            if (!customerRowExists(customer.getModelId())) {
                int col = 1;

                insertStatement.setString(col++, customer.getName());
                insertStatement.setString(col++, customer.getLastname());
                insertStatement.setString(col++, customer.getGender().name());
                insertStatement.setString(col++, customer.getIdNumber());
                insertStatement.setString(col++, customer.getBirthDate().toString());

                insertStatement.executeUpdate();
            } else {
                int col = 1;

                updateStatement.setString(col++, customer.getName());
                updateStatement.setString(col++, customer.getLastname());
                updateStatement.setString(col++, customer.getGender().name());
                updateStatement.setString(col++, customer.getIdNumber());
                updateStatement.setString(col++, customer.getBirthDate().toString());

                updateStatement.setInt(col++, customer.getModelId());

                updateStatement.executeUpdate();
            }

            updateStatement.close();
            insertStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean invoicedCustomerExists(int id) {
        String query = "SELECT customer.* FROM invoice INNER JOIN customer ON invoice.customer_id = customer.customer_id WHERE customer.customer_id = ?";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, id);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void deleteCustomerContactRelationship(int customerId, int contactId) {
        String query = "DELETE FROM shop_user_contact WHERE customer_id = ? AND contact_id = ?";

        PreparedStatement deleteStmt;

        try {
            deleteStmt = connection.prepareStatement(query);

            deleteStmt.setInt(1, customerId);
            deleteStmt.setInt(2, contactId);

            deleteStmt.executeUpdate();
            deleteStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerTableRow(int id) {
        if (customerRowExists(id)) {
            String query = "DELETE FROM customer WHERE customer_id = ?";

            PreparedStatement deleteStmt;

            try {
                deleteStmt = connection.prepareStatement(query);

                deleteStmt.setInt(1, id);

                deleteStmt.executeUpdate();
                deleteStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No record to delete", null, JOptionPane.WARNING_MESSAGE);
        }
    }

    /************************* SUPPLIER **********************/
    public boolean supplierRowExists(int id) {
        int count = 0;

        try {
            String checkSql = "SELECT count(*) AS count FROM supplier WHERE supplier_id=?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);

            checkStmt.setInt(1, id);

            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            count = checkResult.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count > 0) {
            return true;
        }

        return false;
    }

    public int getHighestSupplierId() {
        String query = "SELECT supplier_id FROM supplier WHERE supplier_id = (SELECT MAX(supplier_id) FROM supplier)";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                int id = Integer.valueOf(resultsList.getString(1));

                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getSupplierCount() {
        String query = "SELECT COUNT(*) AS supplier_count FROM supplier";

        Statement stmt;

        try {
            stmt = connection.createStatement();
            ResultSet resultsSet = stmt.executeQuery(query);
            resultsSet.next();
            int count = resultsSet.getInt("supplier_count");
            resultsSet.close();

            return count;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public List<Supplier> fetchSuppliersListFromDatabase(int limit, int offset) {
        List<Supplier> suppliersList = new ArrayList<>();
        Supplier supplier;

        String query = "SELECT * FROM supplier ORDER BY creation_date DESC LIMIT ? OFFSET ?";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, limit);
            readStmt.setInt(2, offset);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                int supplierId = resultsList.getInt("supplier_id");
                String name = resultsList.getString("name");
                String vatNumber = resultsList.getString("vat_number");
                boolean isBusiness = resultsList.getBoolean("is_business");

                supplier = new Supplier(supplierId, name, vatNumber, isBusiness);

                suppliersList.add(supplier);
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return suppliersList;
    }

    public List<Supplier> fetchFilteredSuppliersListFromDatabase(String filter) {
        Supplier supplier;
        List<Supplier> suppliersList = new ArrayList<>();

        String query = "SELECT supplier.* FROM shop_user_contact INNER JOIN supplier ON shop_user_contact.supplier_id = supplier.supplier_id \n"
                + "INNER JOIN contact ON shop_user_contact.contact_id = contact.contact_id \n"
                + "WHERE supplier.supplier_id LIKE '%" + filter + "%' OR supplier.name LIKE '%" + filter
                + "%' OR supplier.vat_number LIKE '%" + filter + "%' \n" + "OR contact.phone LIKE '%" + filter
                + "%' OR contact.email LIKE '%" + filter + "%' \n" + " OR contact.address LIKE '%" + filter
                + "%' OR contact.zip_code LIKE '%" + filter + "%' OR contact.city LIKE '%" + filter + "%' \n"
                + " OR contact.province LIKE '%" + filter + "%' OR contact.country LIKE '%" + filter
                + "%' ORDER BY supplier.creation_date DESC";

        Statement readStmt;

        try {
            readStmt = connection.createStatement();

            ResultSet resultsList = readStmt.executeQuery(query);

            // load the whole list
            while (resultsList.next()) {
                int supplierId = Integer.valueOf(resultsList.getInt("supplier_id"));
                String name = resultsList.getString("name");
                String vatNumber = resultsList.getString("vat_number");
                boolean isBusiness = resultsList.getBoolean("is_business");

                supplier = new Supplier(supplierId, name, vatNumber, isBusiness);

                suppliersList.add(supplier);
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return suppliersList;
    }

    public Supplier fetchSupplierFromTableRow(int id) {
        Supplier supplier;
        String query = "SELECT * FROM supplier WHERE supplier_id = ?";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, id);

            ResultSet resultsList = readStmt.executeQuery();

            int supplierId = id;
            String supplierName;
            String supplierVatNumber;
            boolean supplierIsBusines;

            while (resultsList.next()) {
                supplierName = resultsList.getString("name");
                supplierVatNumber = resultsList.getString("vat_number");
                supplierIsBusines = Boolean.valueOf(resultsList.getBoolean("is_business"));

                supplier = new Supplier(supplierId, supplierName, supplierVatNumber, supplierIsBusines);

                return supplier;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveSupplier(Supplier supplier) {
        try {
            String insertSql = "INSERT INTO supplier (name, vat_number, is_business) VALUES (?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);

            String updateSql = "UPDATE supplier SET name = ?, vat_number = ?, is_business = ? WHERE supplier_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);

            if (!supplierRowExists(supplier.getModelId())) {
                int col = 1;

                insertStatement.setString(col++, supplier.getName());
                insertStatement.setString(col++, supplier.getVatNumber());
                insertStatement.setBoolean(col++, supplier.isIsBusiness());

                insertStatement.executeUpdate();
            } else {
                int col = 1;

                updateStatement.setString(col++, supplier.getName());
                updateStatement.setString(col++, supplier.getVatNumber());
                updateStatement.setBoolean(col++, supplier.isIsBusiness());

                updateStatement.setInt(col++, supplier.getModelId());

                updateStatement.executeUpdate();
            }

            updateStatement.close();
            insertStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSupplierContactRelationship(int supplierId, int contactId) {
        String query = "DELETE FROM shop_user_contact WHERE supplier_id = ? AND contact_id = ?";

        PreparedStatement deleteStmt;

        try {
            deleteStmt = connection.prepareStatement(query);

            deleteStmt.setInt(1, supplierId);
            deleteStmt.setInt(2, contactId);

            deleteStmt.executeUpdate();
            deleteStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSupplierTableRow(int id) {
        if (supplierRowExists(id)) {
            String query = "DELETE FROM supplier WHERE supplier_id = ?";

            PreparedStatement deleteStmt;

            try {
                deleteStmt = connection.prepareStatement(query);

                deleteStmt.setInt(1, id);

                deleteStmt.executeUpdate();
                deleteStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No record to delete", null, JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public boolean suppliedProductExists(int id){
        String query = "SELECT supplier.* FROM product INNER JOIN supplier ON product.supplier_id = supplier.supplier_id WHERE product.supplier_id = ?";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, id);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /************************* CONTACT **********************/
    public boolean contactRowExists(int id) {
        int count = 0;

        try {
            String checkSql = "SELECT count(*) AS count FROM contact WHERE contact_id=?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);

            checkStmt.setInt(1, id);

            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            count = checkResult.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count > 0) {
            return true;
        }

        return false;
    }

    public int getHighestContactId() {
        String query = "SELECT contact_id FROM contact WHERE contact_id = (select MAX(contact_id) FROM contact)";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                int id = Integer.valueOf(resultsList.getString(1));

                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean customerContactRowExists(int id) {
        String query = "SELECT contact.*\r\n"
                + "FROM shop_user_contact INNER JOIN contact ON shop_user_contact.contact_id = contact.contact_id\r\n"
                + "WHERE shop_user_contact.customer_id = ? AND shop_user_contact.supplier_id IS NULL";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, id);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean supplierContactRowExists(int id) {
        String query = "SELECT contact.*\r\n"
                + "FROM shop_user_contact INNER JOIN contact ON shop_user_contact.contact_id = contact.contact_id\r\n"
                + "WHERE shop_user_contact.customer_id IS NULL AND shop_user_contact.supplier_id = ?";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, id);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public Contact fetchCustomerContactRow(int id) {
        Contact contact;
        String query = "SELECT contact.*\r\n"
                + "FROM shop_user_contact INNER JOIN contact ON shop_user_contact.contact_id = contact.contact_id\r\n"
                + "WHERE shop_user_contact.customer_id = ? AND shop_user_contact.supplier_id IS NULL";

        PreparedStatement readStmt;

        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, id);

            ResultSet resultsList = readStmt.executeQuery();

            // load the whole list
            while (resultsList.next()) {
                int contactId = resultsList.getInt("contact_id");
                String phone = resultsList.getString("phone");
                String email = resultsList.getString("email");
                String address = resultsList.getString("address");
                String zipCode = resultsList.getString("zip_code");
                String city = resultsList.getString("city");
                String province = resultsList.getString("province");
                String country = resultsList.getString("country");

                contact = new Contact(contactId, phone, email, address, zipCode, city, province, country);

                return contact;
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Contact fetchSupplierContactRow(int id) {
        Contact contact;
        String query = "SELECT contact.*\r\n"
                + "FROM shop_user_contact INNER JOIN contact ON shop_user_contact.contact_id = contact.contact_id\r\n"
                + "WHERE shop_user_contact.customer_id IS NULL AND shop_user_contact.supplier_id = ?";

        PreparedStatement readStmt;

        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, id);

            ResultSet resultsList = readStmt.executeQuery();

            // load the whole list
            while (resultsList.next()) {
                int contactId = resultsList.getInt("contact_id");
                String phone = resultsList.getString("phone");
                String email = resultsList.getString("email");
                String address = resultsList.getString("address");
                String zipCode = resultsList.getString("zip_code");
                String city = resultsList.getString("city");
                String province = resultsList.getString("province");
                String country = resultsList.getString("country");

                contact = new Contact(contactId, phone, email, address, zipCode, city, province, country);

                return contact;
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void saveContact(Contact contact) {
        try {
            String insertSql = "INSERT INTO contact (phone, email, address, zip_code, city, province, country) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);

            String updateSql = "UPDATE contact SET phone = ?, email = ?, address = ?, zip_code = ?, city = ?, province = ?, country = ? WHERE contact_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);

            if (!contactRowExists(contact.getModelId())) {
                int col = 1;

                insertStatement.setString(col++, contact.getPhone());
                insertStatement.setString(col++, contact.getEmail());
                insertStatement.setString(col++, contact.getAddress());
                insertStatement.setString(col++, contact.getZipCode());
                insertStatement.setString(col++, contact.getCity());
                insertStatement.setString(col++, contact.getProvince());
                insertStatement.setString(col++, contact.getCountry());

                insertStatement.executeUpdate();
            } else {
                int col = 1;

                updateStatement.setString(col++, contact.getPhone());
                updateStatement.setString(col++, contact.getEmail());
                updateStatement.setString(col++, contact.getAddress());
                updateStatement.setString(col++, contact.getZipCode());
                updateStatement.setString(col++, contact.getCity());
                updateStatement.setString(col++, contact.getProvince());
                updateStatement.setString(col++, contact.getCountry());

                updateStatement.setInt(col++, contact.getModelId());

                updateStatement.executeUpdate();
            }

            updateStatement.close();
            insertStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertCustomerIdIntoRelationalTable(int customerId, int contactId) {
        String query = "INSERT INTO shop_user_contact (customer_id, contact_id) VALUES (?, ?)";

        PreparedStatement insertStmt;
        try {
            insertStmt = connection.prepareStatement(query);

            insertStmt.setInt(1, customerId);
            insertStmt.setInt(2, contactId);

            insertStmt.executeUpdate();
            insertStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertSupplierIdIntoRelationalTable(int supplierId, int contactId) {
        String query = "INSERT INTO shop_user_contact (supplier_id, contact_id) VALUES (?, ?)";

        PreparedStatement insertStmt;
        try {
            insertStmt = connection.prepareStatement(query);

            insertStmt.setInt(1, supplierId);
            insertStmt.setInt(2, contactId);

            insertStmt.executeUpdate();
            insertStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int id) {
        if (contactRowExists(id)) {
            String query = "DELETE FROM contact WHERE contact_id = ?";

            PreparedStatement deleteStmt;

            try {
                deleteStmt = connection.prepareStatement(query);

                deleteStmt.setInt(1, id);

                deleteStmt.executeUpdate();
                deleteStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /************************* CATEGORY **********************/
    public List<ProductCategory> fetchCategoriesListFromDatabase() {
        List<ProductCategory> categoriesList = new ArrayList<>();
        ProductCategory productCategory;

        String query = "SELECT * FROM product_category";

        try {
            Statement readStmt = connection.createStatement();

            ResultSet resultsList = readStmt.executeQuery(query);

            // load the whole list
            while (resultsList.next()) {
                int categoryId = Integer.valueOf(resultsList.getInt("product_category_id"));
                String categoryName = resultsList.getString("category_name");

                productCategory = new ProductCategory(categoryId, categoryName);

                categoriesList.add(productCategory);
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return categoriesList;
    }

    /************************** PRODUCT **********************/
    public boolean productRowExists(int id) {
        int count = 0;

        try {
            String checkSql = "SELECT count(*) AS count FROM product WHERE product_id=?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);

            checkStmt.setInt(1, id);

            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            count = checkResult.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count > 0) {
            return true;
        }

        return false;
    }

    public int getProductCount(int categoryId) {
        int count = 0;
        String query = "SELECT COUNT(*) AS product_count FROM product WHERE product_category_id = ? && is_purchased = 0 && in_cart = 0 && invoice_id IS NULL";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, categoryId);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                // create new object with the fields from the updated record
                count = resultsList.getInt("product_count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public List<Product> fetchProductsListFromDatabase(int foreignKey, int limit, int offset) {
        List<Product> productsList = new ArrayList<>();
        Product product;

        String query = "SELECT * FROM product WHERE product_category_id = ? && is_purchased = 0 && in_cart = 0 && invoice_id IS NULL ORDER BY added_on DESC LIMIT "
                + limit + " OFFSET " + offset;

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, foreignKey);

            ResultSet resultsList = readStmt.executeQuery();

            // load the whole list
            while (resultsList.next()) {
                int productId = resultsList.getInt("product_id");
                String name = resultsList.getString("name");
                String description = resultsList.getString("description");
                double acquisitionPrice = resultsList.getDouble("acquisition_price");
                double sellingPrice = resultsList.getDouble("selling_price");
                int itemsPerUnit = resultsList.getInt("items_per_unit");
                ProductCondition condition = ProductCondition.valueOf(resultsList.getString("product_condition"));
                String addedOn = resultsList.getString("added_on");
                int supplierId = resultsList.getInt("supplier_id");

                product = new Product(productId, name, description, acquisitionPrice, sellingPrice, itemsPerUnit,
                        condition, addedOn, foreignKey, supplierId, null);

                productsList.add(product);
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return productsList;
    }

    public List<Product> fetchFilteredProductsListFromDatabase(String filter, int foreignKey) {
        List<Product> productsList = new ArrayList<>();
        Product product;

        String query = "SELECT * FROM product WHERE (product_id LIKE '%" + filter + "%' OR name LIKE '%" + filter
                + "%' OR description LIKE '%" + filter + "%' \n" + "OR acquisition_price LIKE '%" + filter
                + "%' OR selling_price LIKE '%" + filter + "%' OR items_per_unit LIKE '%" + filter
                + "%' OR product_condition LIKE '%" + filter + "%') "
                + "AND product_category_id = ? && in_cart = 0 && is_purchased = 0 && invoice_id IS NULL ORDER BY added_on DESC";

        PreparedStatement readStmt;

        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setInt(1, foreignKey);

            ResultSet resultsList = readStmt.executeQuery();

            // load the whole list
            while (resultsList.next()) {
                int productId = resultsList.getInt("product_id");
                String productName = resultsList.getString("name");
                String productDescription = resultsList.getString("description");
                double acquisitionPrice = resultsList.getDouble("acquisition_price");
                double sellingPrice = resultsList.getDouble("selling_price");
                int itemsPerUnit = resultsList.getInt("items_per_unit");
                ProductCondition productCondition = ProductCondition.valueOf(resultsList.getString("product_condition"));
                int productCategoryId = foreignKey;
                String addedOn = resultsList.getString("added_on");
                int supplierId = resultsList.getInt("supplier_id");

                product = new Product(productId, productName, productDescription, acquisitionPrice, sellingPrice,
                        itemsPerUnit, productCondition, addedOn, productCategoryId, supplierId, null);

                productsList.add(product);
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return productsList;
    }

    public void saveProduct(Product product) {
        try {
            String insertSql = "INSERT INTO product (name, description, acquisition_price, selling_price, items_per_unit, product_condition, product_category_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);

            String updateSql = "UPDATE product SET name = ?, description = ?, acquisition_price = ?, selling_price = ?, items_per_unit = ?, product_condition = ?, product_category_id = ? WHERE product_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);

            if (!productRowExists(product.getModelId())) {
                int col = 1;

                insertStatement.setString(col++, product.getProductName());
                insertStatement.setString(col++, product.getProductDescription());
                insertStatement.setDouble(col++, product.getAcquisitionPrice());
                insertStatement.setDouble(col++, product.getSellingPrice());
                insertStatement.setInt(col++, product.getProductItemsPerUnit());
                insertStatement.setString(col++, product.getProductCondition().name());
                insertStatement.setInt(col++, product.getProductCategoryId());
                insertStatement.setInt(col++, product.getSupplierId());

                insertStatement.executeUpdate();
            } else {
                int col = 1;

                updateStatement.setString(col++, product.getProductName());
                updateStatement.setString(col++, product.getProductDescription());
                updateStatement.setDouble(col++, product.getAcquisitionPrice());
                updateStatement.setDouble(col++, product.getSellingPrice());
                updateStatement.setInt(col++, product.getProductItemsPerUnit());
                updateStatement.setString(col++, product.getProductCondition().name());
                updateStatement.setInt(col++, product.getProductCategoryId());

                updateStatement.setInt(col++, product.getModelId());

                updateStatement.executeUpdate();
            }

            updateStatement.close();
            insertStatement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteProductTableRow(int id) {
        if (productRowExists(id)) {
            String query = "DELETE FROM product WHERE product_id = ? && is_purchased = 0";

            PreparedStatement deleteStmt;

            try {
                deleteStmt = connection.prepareStatement(query);

                deleteStmt.setInt(1, id);

                deleteStmt.executeUpdate();
                deleteStmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No record to delete", null, JOptionPane.WARNING_MESSAGE);
        }
    }

    public void addProductToCart(int id) {
        String query = "UPDATE product SET in_cart = ? WHERE product_id = ?";

        PreparedStatement updateStmt;

        try {
            updateStmt = connection.prepareStatement(query);

            // setting model info to write into database
            updateStmt.setInt(1, 1);
            updateStmt.setInt(2, id);

            updateStmt.executeUpdate();
            updateStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProductFromCart(int id) {
        String query = "UPDATE product SET in_cart = ? WHERE product_id = ?";

        PreparedStatement updateStmt;

        try {
            updateStmt = connection.prepareStatement(query);

            // setting model info to write into database
            updateStmt.setInt(1, 0);
            updateStmt.setInt(2, id);

            updateStmt.executeUpdate();
            updateStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearShoppingCart() {
        String query = "UPDATE product SET in_cart = ? WHERE in_cart = 1 && is_purchased = 0";

        PreparedStatement updateStmt;

        try {
            if (connection != null) {
                updateStmt = connection.prepareStatement(query);

                //setting model info to write into database
                updateStmt.setInt(1, 0);

                updateStmt.executeUpdate();
                updateStmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> fetchSalesListFromDatabase(String date) {
        List<String> salesInfosList = new ArrayList<>();

        String query = "SELECT product.name, product.selling_price, customer.name, customer.lastname \n"
                + "FROM product INNER JOIN invoice ON product.invoice_id = invoice.invoice_id \n"
                + "INNER JOIN customer ON invoice.customer_id = customer.customer_id\n"
                + "WHERE product.is_purchased = 1 && product.in_cart = 0 && product.invoice_id IS NOT NULL AND YEAR(invoice.invoiced_on) = ? ORDER BY added_on DESC";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            readStmt.setString(1, date);

            ResultSet resultsList = readStmt.executeQuery();

            // load the whole list
            while (resultsList.next()) {
                String productName = resultsList.getString("product.name");
                String sellingPrice = resultsList.getString("product.selling_price");
                String customerName = resultsList.getString("customer.name");
                String customerLastname = resultsList.getString("customer.lastname");

                String saleInfo = productName + " (" + sellingPrice + "€) - sold to: " + customerName + " " + customerLastname;

                salesInfosList.add(saleInfo);
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return salesInfosList;
    }

    /************************* INVOICE **********************/
    public int getInvoiceCount() {
        String query = "SELECT COUNT(*) AS invoice_count FROM invoice";

        Statement stmt;

        try {
            stmt = connection.createStatement();
            ResultSet resultsSet = stmt.executeQuery(query);
            resultsSet.next();
            int count = resultsSet.getInt("invoice_count");
            resultsSet.close();

            return count;
        } catch (SQLException | NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public boolean invoiceRowExists(int id) {
        int count = 0;

        try {
            String checkSql = "SELECT count(*) AS count FROM invoice WHERE invoice_id = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkSql);

            checkStmt.setInt(1, id);

            ResultSet checkResult = checkStmt.executeQuery();
            checkResult.next();

            count = checkResult.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count > 0) {
            return true;
        }

        return false;
    }

    public int getHighestInvoiceId() {
        String query = "SELECT invoice_id FROM invoice WHERE invoice_id = (SELECT MAX(invoice_id) FROM invoice)";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            ResultSet resultsList = readStmt.executeQuery();

            while (resultsList.next()) {
                int id = resultsList.getInt(1); //Integer.valueOf(resultsList.getString(1));

                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<String> fetchInvoiceYears() {
        List<String> invoiceYearsList = new ArrayList<>();

        String query = "SELECT YEAR(invoiced_on) FROM invoice GROUP BY YEAR(invoiced_on) ORDER BY invoiced_on DESC";

        PreparedStatement readStmt;
        try {
            readStmt = connection.prepareStatement(query);

            ResultSet resultsList = readStmt.executeQuery();

            // load the whole list
            while (resultsList.next()) {
                String invoicedOn = resultsList.getString("YEAR(invoiced_on)");

                invoiceYearsList.add(invoicedOn);
            }

            resultsList.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        return invoiceYearsList;
    }

    public void saveInvoice(Invoice invoice) {
        try {
            String insertSql = "INSERT INTO invoice (customer_id, applied_tax, total_amount, discount, total_paid, payment_method, note) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement insertStatement = connection.prepareStatement(insertSql);

            String updateSql = "UPDATE invoice SET applied_tax = ?, total_amount = ?, discount = ?, total_paid = ?, payment_method = ?, note = ?, is_voided = ?, void_reason = ? WHERE invoice_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);

            if (!invoiceRowExists(invoice.getModelId())) {
                int col = 1;

                insertStatement.setInt(col++, invoice.getCustomerId());
                insertStatement.setDouble(col++, invoice.getAppliedTax());
                insertStatement.setDouble(col++, invoice.getTotalAmount());
                insertStatement.setDouble(col++, invoice.getDiscount());
                insertStatement.setDouble(col++, invoice.getTotalPayment());
                insertStatement.setString(col++, invoice.getPaymentMethod().name());
                insertStatement.setString(col++, invoice.getNote());

                insertStatement.executeUpdate();
            } else {
                int col = 1;

                updateStatement.setDouble(col++, invoice.getAppliedTax());
                updateStatement.setDouble(col++, invoice.getTotalAmount());
                updateStatement.setDouble(col++, invoice.getDiscount());
                updateStatement.setDouble(col++, invoice.getTotalPayment());
                updateStatement.setString(col++, invoice.getPaymentMethod().name());
                updateStatement.setString(col++, invoice.getNote());
                updateStatement.setBoolean(col++, invoice.isVoided());
                updateStatement.setString(col++, invoice.getVoidedReason());
                updateStatement.setInt(col++, invoice.getModelId());
            }

            insertStatement.close();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProductTableRow(Product product) {
        String query = "UPDATE product SET in_cart = 0, is_purchased = 1, invoice_id = ? WHERE product_id = ?";

        PreparedStatement updateStmt;

        try {
            updateStmt = connection.prepareStatement(query);

            //setting model info to write into database
            updateStmt.setInt(1, product.getInvoiceId());
            updateStmt.setInt(2, product.getModelId());

            updateStmt.executeUpdate();
            updateStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
