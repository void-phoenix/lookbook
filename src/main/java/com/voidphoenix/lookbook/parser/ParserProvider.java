package com.voidphoenix.lookbook.parser;



import com.voidphoenix.lookbook.parser.impl.AllItEbooksParser;
import com.voidphoenix.lookbook.parser.impl.ItEbooksParser;

import java.util.Arrays;
import java.util.List;

public class ParserProvider {

    public static List<Parser> getParsers() {
        return Arrays.asList(new AllItEbooksParser(), new ItEbooksParser());
    }
}
