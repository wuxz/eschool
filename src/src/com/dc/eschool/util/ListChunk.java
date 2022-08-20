package com.dc.eschool.util;

import java.io.Serializable;
import java.util.Collection;

/**
 * Title:        Eschool
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author kurt Xiang
 * @version 1.0
 */

public class ListChunk implements java.io.Serializable
{

    private int            totalElements;
    private Collection     elementsInThisList;
    private int            firstElementOfThisList;
    private int            countOfElementsInthisList;

    public ListChunk(int count, Collection coll, int first, int curCount)
    {
        this.totalElements = count;
        this.elementsInThisList = coll;
        this.firstElementOfThisList = first;
        this.countOfElementsInthisList = curCount;
    }

    /**
     * Class constructor with no arguments, used by the web tier.
     */
    public ListChunk() {}

    public int getTotalCount()
    {
        return totalElements;
    }

    public Collection getCollection()
    {
        return elementsInThisList;
    }

    public int getCurrentCount()
    {
        return countOfElementsInthisList;
    }

    public int getFirstElementIndex()
    {
        return firstElementOfThisList;
    }
}
