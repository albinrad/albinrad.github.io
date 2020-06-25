package studentcoursemanager.server;

public class MainApp
{
    public static void main(String[] args)
    {
        ServerView theView = new ServerView();
        ServerModel theModel = new ServerModel();
        new ServerController(theView, theModel);
        theView.showMainFrame();
    }
}