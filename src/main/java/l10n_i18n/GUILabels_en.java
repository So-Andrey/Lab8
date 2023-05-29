package l10n_i18n;

import java.util.ListResourceBundle;

public class GUILabels_en extends ListResourceBundle {

    private static final Object[][] contents =
    {
            {"coll_type", "Collection type"},
            {"coll_date", "Initialization date"},
            {"coll_amount", "Number of elements"},
            {"recursion", "Recursion!!!"},
            {"no_access", "Do not have access to file"},
            {"not_found", "File not found"},
            {"no_dragon", "There is no such dragon"},
            {"not_your", "This is not your dragon"},
            {"invalid_inp", "Invalid input"},
            {"no_greater", "There is no greater dragons"},
            {"no_lower", "There is no lower dragons"},
            {"amount", "Amount of deleted dragons"},
            {"count_by_head", "The number of dragons with a given number of eyes"},
            {"delete_ps", "P.S. (You can only delete dragons you create)"},
            {"name", "Name"},
            {"creator", "Creator"},
            {"creation_date", "Creation Date"},
            {"age", "Age"},
            {"coordinates", "Coordinates"},
            {"x", "X Coordinate"},
            {"y", "Y Coordinate"},
            {"color", "Color"},
            {"type", "Type"},
            {"character", "Character"},
            {"eyes_count", "Eyes Count"},
            {"if_min", "If min"},
            {"submit", "Submit"},
            {"too_old", "Too old"},
            {"cleaned", "Your part of collection has been cleaned"},
            {"help", """
                    Map : open the map with objects
                    Info : display information about the collection (type, initialization date, number of items)
                    Add (If min) : add a new item to the collection (if it is the smallest)
                    Remove by id : remove an item from the collection by its id
                    Remove greater : remove all items greater than the one specified by id from the collection.
                    Remove lower : remove all items smaller than the one specified by id from the collection
                    Clear : clear the part of the collection you built
                    Execute script : read and execute the script from the given file
                    Update : refresh the values of the object (you can do it by double clicking on a cell)
                    Count By head eyes_count : show the number of elements whose value is equal to the given one
                    You can delete or change the dragon only if you are its creator!
                    """},
            {"script_help", """
                    add {element} : add a new element to the collection
                    add_if_min {element} : add a new element to the collection if its value is less than the smallest element of this collection
                    execute_script file_name : read and execute a script from the specified file. The script contains commands in the same form as the input
                    clear : clear the collection
                    update id {field} {new value} : update the value of an element in the collection whose id is equal to the specified one
                    remove_by_id id : remove an item from the collection by its id
                    remove_greater id : remove all elements greater than the given one from the collection
                    remove_lower id : remove all elements smaller than the given one from the collection
                    count_by_head eyes_count : print the number of elements whose head value is equal to the given one
                    """},
            {"edit", "You can only edit dragons you create"},
            {"search", "Search"},
            {"add", "ADD"},
            {"remove", "REMOVE"},
            {"remove_by_id", "REMOVE BY ID"},
            {"remove_lower", "REMOVE LOWER"},
            {"remove_greater", "REMOVE GREATER"},
            {"clear", "CLEAR"},
            {"execute_script", "EXECUTE SCRIPT"},
            {"count_by_head", "COUNT BY HEAD"},
            {"info", "INFO"},
            {"help_button", "HELP"},
            {"map", "MAP"},
            {"log_out", "Log Out"},
            {"user", "CURRENT USER"},
            {"table", "TABLE"},
            {"login", "Login"},
            {"password", "Password"},
            {"reg","REGISTRATION"},
            {"auth", "AUTHORIZATION"},
            {"already_reg", "Already registered? Log In"},
            {"become", "Don't have an account? Sign Up"},
            {"log_in", "Log In"},
            {"login_exists", "Login already exists"}


    };

    public Object[][] getContents() {
        return contents;
    }

}
