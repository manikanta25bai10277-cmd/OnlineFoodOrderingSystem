package service;

import model.Admin;

public class AdminService {

    public void displayAdminDetails(Admin admin) {
        admin.showAdminDetails();
    }

    public void manageRestaurant(Admin admin) {
        admin.manageRestaurant();
    }

    public boolean login(Admin admin,
                         String email,
                         String password) {

        return admin.getEmail().equals(email)
                && admin.getPassword().equals(password);
    }
}