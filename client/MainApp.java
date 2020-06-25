package studentcoursemanager.client;

import studentcoursemanager.client.frontend.ClientView;

public class MainApp
{
    public static void main(String[] args)
    {
        ClientView theView = new ClientView();
        ClientModel theModel = new ClientModel();
        new ClientController(theView, theModel);
        theView.showConnectFrame();
    }
}