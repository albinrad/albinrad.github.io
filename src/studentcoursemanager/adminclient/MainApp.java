package studentcoursemanager.adminclient;

import studentcoursemanager.adminclient.frontend.AdminView;

public class MainApp
{

    public static void main(String[] args)
    {
        AdminView theView = new AdminView();
        AdminModel theModel = new AdminModel();
        new AdminController(theView, theModel);
        theView.showConnectToServerFrame();
    }
}
