import java.io.File;

public class FileGetter{
    private final int FILENAME_POS;

    public FileGetter(int FILENAME_POS) {
        this.FILENAME_POS = FILENAME_POS;
    }

    private String getFilename(String[] args, int argNum)throws RuntimeException{
        if (args.length < argNum)
            throw new RuntimeException("Too few arguments");
        else
        if (args.length > argNum)
            throw new RuntimeException("Too many arguments");
        return args[0];
    }

    public static void errorMsg(String Msg){
        System.err.println(Msg);
        new java.util.Scanner(System.in).nextLine();
        System.exit(1);
    }

    public File getFile(String args[], int argNum){
        String filename = null;
        try{
            filename = getFilename(args, argNum);
        }
        catch (RuntimeException e){
            errorMsg("Wrong format(" + e.getMessage() + ").");
        }

        File file = new File(filename);
        if (! file.exists())
            errorMsg("Wrong filename " + filename + ". This file doesn`t exist!");
        return file;
    }
}
