package com.app.monitoring;

import com.sun.jna.Structure;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;


public class FileDescriptorUtil {

//    public interface CLibrary extends Library {
//        CLibrary INSTANCE = Native.load("c", CLibrary.class);
//
//        int getrlimit(int resource, rlimit rlim);
//    }
//
//    public static NativeLong getFileOpenCount() {
//        rlimit rlim = new rlimit();
//        int result = CLibrary.INSTANCE.getrlimit(7, rlim); // 7 represents RLIMIT_NOFILE
//
//        if (result == 0) {
//            System.out.println("Maximum open file descriptors: " + rlim.rlim_cur);
//            return rlim.rlim_cur;
//        } else {
//            System.err.println("Failed to retrieve file descriptor limit.");
//            return null;
//        }
//    }
//
//    public static class rlimit extends Structure {
//        public NativeLong rlim_cur;
//        public NativeLong rlim_max;
//    }
}