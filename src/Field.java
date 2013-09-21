
public class Field {
	public static int density = 10;
	public Vector[][] data;
	String function;
	
	Field(int windowY, int windowX){
		data = new Vector[windowX][windowY];
	}
	
	Field(int windowY, int windowX, int xv, int yv){ //constant vector field with div and curl = 0
		data = new Vector[windowY/density][windowX/density];
		for (int i=0; i<windowY; i++)
			for (int j=0; j<windowX; j++)
				data[i][j] = new Vector(xv, yv);
		
	}
	
	public Vector getVectorAt(int x, int y){
		return data[y][x];
	}
	
	public void setVectorAt(int x, int y, int xv, int yv){
		data[y][x] = new Vector(xv, yv);
	}
}
