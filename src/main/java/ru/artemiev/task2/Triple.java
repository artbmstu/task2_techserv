package ru.artemiev.task2;

class Triple<K,T,V> {
    private K i;
    private T s1;
    private V s2;

    Triple(K i, T s1, V s2){
        this.i = i;
        this.s1 = s1;
        this.s2 = s2;
    }
    @Override
    public String toString() {
        return "(" + i + "," + s1 + "," + s2 +")";
    }
}