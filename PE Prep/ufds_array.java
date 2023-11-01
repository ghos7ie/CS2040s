class ufds_array { // OOP style
    private int[] p, rank, setSize;
    private int numSets;

    public ufds_array(int N) {
        p = new int[N];
        rank = new int[N];
        setSize = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            setSize[i] = 1;
        }
    }

    public int findSet(int i) {
        if (p[i] == i)
            return i;
        else {
            int ret = findSet(p[i]);
            p[i] = ret;
            return ret;
        }
    }

    public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        int x = findSet(i), y = findSet(j);
        if (x != y) {
            numSets--;
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) {
                p[y] = x;
                setSize[x] = setSize[x] + setSize[y];
            } else {
                p[x] = y;
                setSize[y] = setSize[y] + setSize[x];
                if (rank[x] == rank[y])
                    rank[y]++;
            }
        }
    }

    public int numDisjointSets() {
        return numSets;
    }

    public int sizeOfSet(int i) {
        return setSize[findSet(i)];
    }
}