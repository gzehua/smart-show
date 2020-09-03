package com.coder.zzq.smartshow.dialog.data_item;

public abstract class DataItem {
    public static final String DATA_CAST_EXCEPTION_DESC = "the data can not cast into type:";
    protected boolean mInitialData = true;
    protected String mName;

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }


    public char toChar() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "char");
    }

    public byte toByte() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "byte");
    }

    public short toShort() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "short");
    }

    public int toInt() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "int");
    }

    public long toLong() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "long");
    }

    public float toFloat() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "float");
    }

    public double toDouble() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "double");
    }

    public boolean toBoolean() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "boolean");
    }

    public String toText() {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + "String");
    }

    public Object toData() {
        throw new UnsupportedOperationException("unsupported operation:toData");
    }

    public <Data> Data toData(Class<Data> dataClass) {
        throw new UnsupportedOperationException(DATA_CAST_EXCEPTION_DESC + dataClass.getCanonicalName());
    }


    public abstract boolean isPrimitive();


    protected abstract boolean judgeDataChanged();


    public final boolean isDataChanged() {
        return mInitialData || judgeDataChanged();
    }


    public final void updateData() {
        mInitialData = false;
        onUpdateData();
    }

    protected abstract void onUpdateData();


    public void reset() {
        mInitialData = true;
    }

}
