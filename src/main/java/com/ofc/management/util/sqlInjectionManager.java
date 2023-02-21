package com.ofc.management.util;

public class sqlInjectionManager {

    public String parse(String input) {
        String output = input;
        output = output.replace(";", "");
        output = output.replace("--", "");
        output = output.replace("/*", "");
        output = output.replace("*/", "");
        output = output.replace("=", "");
        output = output.replace(">", "");
        output = output.replace("<", "");
        output = output.replace("!", "");
        output = output.replace("?", "");
        output = output.replace("(", "");
        output = output.replace(")", "");
        output = output.replace(" ", "");
        output = output.replace("OR", "");
        output = output.replace("AND", "");
        output = output.replace("SELECT", "");
        output = output.replace("INSERT", "");
        output = output.replace("UPDATE", "");
        output = output.replace("DELETE", "");
        output = output.replace("DROP", "");
        output = output.replace("CREATE", "");
        output = output.replace("ALTER", "");
        output = output.replace("TRUNCATE", "");
        output = output.replace("REPLACE", "");
        output = output.replace("GRANT", "");
        output = output.replace("REVOKE", "");
        output = output.replace("LOCK", "");
        output = output.replace("UNLOCK", "");
        output = output.replace("EXEC", "");
        output = output.replace("EXECUTE", "");
        output = output.replace("DECLARE", "");
        output = output.replace("FETCH", "");
        output = output.replace("OPEN", "");
        output = output.replace("CLOSE", "");
        //non capital sql words
        output = output.replace("select", "");
        output = output.replace("insert", "");
        output = output.replace("update", "");
        output = output.replace("delete", "");
        output = output.replace("drop", "");
        output = output.replace("create", "");
        output = output.replace("alter", "");
        output = output.replace("truncate", "");
        output = output.replace("replace", "");
        output = output.replace("grant", "");
        output = output.replace("revoke", "");
        output = output.replace("lock", "");
        output = output.replace("unlock", "");
        output = output.replace("exec", "");
        output = output.replace("execute", "");
        output = output.replace("declare", "");
        output = output.replace("fetch", "");
        output = output.replace("open", "");
        output = output.replace("close", "");
        return output;
    }
}
