import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

//Sparse Matrix ADT
public class SparseMatrix {

	// nested class for each non-zero entry in Sparse Matrix
    class Entry {
        public int row, col, value;

        @Override
        public String toString() {
            return "Entry{" +
                "row=" + row +
                ", col=" + col +
                ", value=" + value +
                '}';
        }

        public Entry(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    private int rowCount;
    private int colCount;
    private int entryCount;
    private Entry[] data;
    private int nextSlot;

    private SparseMatrix(int rowCount, int colCount, int entryCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.entryCount = entryCount;

        data = new Entry [entryCount];
        nextSlot = 0;
    }

    /*
	 * Construct a sparse matrix by reading data from a specified file
	 */
    public static SparseMatrix create(String filename) throws IOException {
        Path path = Paths.get(filename);
        if (!Files.exists(path))
            throw new IllegalArgumentException("No such file");

        SparseMatrix matrix = null;
        int row, col, count, value;
        try (Scanner scanner = new Scanner(path)) {
            row = scanner.nextInt();
            col = scanner.nextInt();
            count = scanner.nextInt();
            matrix = new SparseMatrix(row, col, count);

            for (int i = 0; i < count; i++) {
                row = scanner.nextInt();
                col = scanner.nextInt();
                value = scanner.nextInt();
                matrix.put(row, col, value);
            }
        };

        return matrix;
    }

    /*
	 * You can define additional private fields and/or methods here, if necessary.
	 */
    
    private void put(int row, int col, int value) {
    	data[nextSlot++] = new Entry(row, col, value);
	}

    /*
	 * Add this matrix with another matrix other.
	 * Assume that the dimensions of two matrices are always compatible.
	 */
	public SparseMatrix add(SparseMatrix other) {
		int c = 1;
		int cot = 0;
		
		cot = this.entryCount + other.entryCount;
		for(int i = 1; i < this.entryCount; i++){
			for(int j = 1; j < other.entryCount; j++){
				if((this.data[i].row == other.data[j].row) && (this.data[i].col == other.data[j].col)){
					cot -= 1;// cot : ���ο� ����� ���� ũ�⸦ ������
				}
			}
		}
		//System.out.println(cot);
		
		SparseMatrix C = new SparseMatrix(this.rowCount, this.colCount, cot);
		
		int thisM[][]; //���� ����� ������ 2���� �迭 ����
		thisM = new int[this.rowCount][this.colCount];
		
		for(int a=0; a<thisM.length; a++){
			for(int b=0; b<thisM.length; b++){
				thisM[a][b] = 0; // 0���� �ʱ�ȭ
			}
		}
		for(int k=0; k < this.entryCount; k++){
			for(int r=0; r < other.entryCount; r++){
				if(this.data[k].row == other.data[r].row && this.data[k].col == other.data[r].col){
					thisM[this.data[k].row][this.data[k].col] = this.data[k].value + other.data[r].value;
				}// ���Ҽ� ������ ���ϰ�
				else{
					thisM[this.data[k].row][this.data[k].col] = this.data[k].value;
					thisM[other.data[r].row][other.data[r].col] = other.data[r].value;
				}// �ƴϸ� ���� ���� ����
			}
		}
		
		for(int x=0; x<thisM.length; x++){
			for(int y=0; y<thisM.length; y++){
				if(thisM[x][y] != 0){
					C.put(x, y, thisM[x][y]);//thisM�� �ִ� 0�� �ƴ� ������ ������ C�� �ű���
				}
			}
		}
		//C.put(6,6,cot);
//		for(c = 0; c < this.entryCount; c++){
//			nextSlot = 0;
//			C.put(this.data[c].row,this.data[c].col,this.data[c].value); // A����� C��Ŀ� ������ ����
//		}
//		for(int k = 0; k < this.entryCount; k++){
//			for(int r = 0; r < other.entryCount; r++){
//				if(this.data[k].row == other.data[r].row && this.data[k].col == other.data[r].col){
//					this.data[k].value += other.data[r].value;//���� ������ A��İ� B��Ŀ� ���� ��� ���� �ִٸ� ���� ���ϰ�
//				}
//				else{
//					nextSlot = this.entryCount + 1;
//					C.put(other.data[r].row, other.data[r].col, other.data[r].value); //A����� ��� ���� �ٸ� ��� ���� B��Ŀ� ������ A��Ŀ� �߰��Ѵ�.
//				}
//			}
//		}
//				if(this.data[i].row == other.data[j].row && this.data[i].col == other.data[j].col){
//					C.put(this.data[i].row, this.data[i].col, this.data[i].value + other.data[j].value);
//				}
//				else if(){
//					C.put(other.data[j].row,other.data[j].col,other.data[j].value);
//				}
		return C;
    }

	/*
	 * Transpose matrix
	 */
    public SparseMatrix transpose() {
    	SparseMatrix trans = new SparseMatrix(6,6,this.entryCount);
    	for(int t = 0; t < this.entryCount; t++){
    		trans.put(this.data[t].col, this.data[t].row, this.data[t].value);// trans��� ��ġ����� �����
    	}
    	
    	Entry mi;
    	int priority = 0;
    	Entry temp = new Entry(0,0,0);
    	
    	//System.out.println(trans.entryCount);
    	for(int q = 0; q < trans.entryCount; q++){//��ü�� �ѹ� ����.
    		mi = trans.data[q];
    		for(int v=q; v < trans.entryCount-1; v++){//�������� �� �� ���鼭 ���� ���� row�� ���� ã�� mi�� �ִ´�.
    			if(mi.row > trans.data[v+1].row){
    				mi = trans.data[v+1];
    				priority = v+1;
    				temp = trans.data[q];
    				trans.data[q] = mi;
    				trans.data[priority] = temp;
    				if(q == 6){
    					System.out.println(mi.row + ", " + mi.col  + ", " + priority);
    				}
    			}
    			else if((mi.row == trans.data[v+1].row) && (mi.col > trans.data[v+1].col)){
    				mi = trans.data[v+1];
    				priority = v+1;
    				temp = trans.data[q];
    				trans.data[q] = mi;
    				trans.data[priority] = temp;
    			}
    		}
    		//temp = trans.data[q]; //temp�� ���� q��°�� ���� ���� ����
    		//trans.data[q] = mi; // q��°�� ���� ���� ���� �;� �ϹǷ� �տ��� ã�� mi�� �ִ´�.
    		//trans.data[priority] = temp;  //mi�� �ִ� �ڸ��� temp�� ����� ���� �ִ´�. �̷ν� swap�Ϸ�
    	}
    	
    	return trans;
    }

    public void print() {
    	System.out.println(this);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(rowCount + ", " + colCount + ", " + entryCount + "\n");
        for (int i = 0; i < entryCount; i++) {
            builder.append(data[i].row + ", " + data[i].col + ", " + data[i].value + "\n");
        }
        return builder.toString();
    }

		/*
		 * DO NOT MODIFY CODE BELOW!!!!!
		 */
    public static void main(String... args) throws IOException {
        if (args[0].equals("p")) { // Print current matrix
            System.out.println("Printing a matrix: " + args[1]);
            SparseMatrix m = SparseMatrix.create(args[1]);
            m.print();
        } else if (args[0].equals("a")) { // Add two matrices
            System.out.println("Adding two matrices: " + args[1] + " and " + args[2] + "\n");
            SparseMatrix A = SparseMatrix.create(args[1]);
            System.out.println("Matrix A = \n" + A);
            SparseMatrix B = SparseMatrix.create(args[2]);
            System.out.println("Matrix B = \n" + B);

            System.out.println("Matrix A + B = \n" + A.add(B));
        } else if (args[0].equals("t")) {   // Transpose a matrix
            System.out.println("Transposing a matrix: " + args[1]);
            SparseMatrix matrix = SparseMatrix.create(args[1]);
            System.out.println(matrix);
            SparseMatrix transposedMatrix = matrix.transpose();
            System.out.println("Transposed Matrix = \n" + transposedMatrix);
        } else {
            System.err.println("Unknown operation ...");
        }
    }
}
