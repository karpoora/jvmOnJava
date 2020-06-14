package com.wangzhen.jvm.classConstant;

import com.wangzhen.jvm.classPackage.ClassReader;
import com.wangzhen.jvm.utils.ByteUtils;

public class ConstantIntegerInfo extends ConstantInof{
    public int value;

    public ConstantIntegerInfo(ClassReader classReader) {
        readInfo(classReader);
    }

    @Override
    void readInfo(ClassReader classReader) {
        byte [] intBytes = classReader.readNByte(4);
        value = ByteUtils.bytesToInt(intBytes);
    }
}
