package zkl.dietitian.entity.log;


public class MapEntity {
	
	
		private String  stringLabels ;
		private String  intdatesets;
		private int ArrayNum ;
   
		public int getArrayNum() {
			return ArrayNum;
		}

		public void setArrayNum(int arrayNum) {
			ArrayNum = arrayNum;
		}

		public void setStringLabels(String  stringLabels){
			
			this.stringLabels = stringLabels;
			
		}
		
        public void setIntdatesets(String  intdatesets){
			
			this.intdatesets = intdatesets;
			
		}
        
        public String  getStringLabels(){
			
			return this.stringLabels;
			
		}
		
        public String  getIntdatesets(){
			
        	return this.intdatesets ;
			
		}
        
}
