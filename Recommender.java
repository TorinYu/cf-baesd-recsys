import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Recommender {

	public static void main(String[] argv) {
		String trainFile = argv[0];
		String testFile = argv[1];
		String outputFile = argv[2];

		//Use arrayList to all the user's information 
		//Use Invert-List to store User's ID and his movie rating value 
		ArrayList<E>
		Scanner scan = new Scanner(new File(args[0]));
		// If you see these outputs, it means you have successfully compiled and run the code.
		// Then you can remove these three lines if you want.
		System.out.println("Training File : " + trainFile);
		System.out.println("Test File : " + testFile);
		System.out.println("Output File : " + outputFile);
		
		
		//http://blog.csdn.net/luowen3405/article/details/6278764

		// Implement your recommendation modules using trainFile and testFile.
		// And output the prediction scores to outputFile.

	}

}


public static void main(String[] args) throws Exception {
    
    // must supply parameter file 
    if (args.length < 1) {
      System.err.println(usage);
      System.exit(1);
    }

    // read in the parameter file; one parameter per line in format of key=value
    Map<String, String> params = new HashMap<String, String>();
    Scanner scan = new Scanner(new File(args[0]));
    String line = null;
    do {
      line = scan.nextLine();
      String[] pair = line.split("=");
      params.put(pair[0].trim(), pair[1].trim());
    } while (scan.hasNext());
    
    // parameters required for this example to run
    if (!params.containsKey("indexPath")) {
      System.err.println("Error: Parameters were missing.");
      System.exit(1);
    }

    // open the index
    READER = DirectoryReader.open(FSDirectory.open(new File(params.get("indexPath"))));

    if (READER == null) {
      System.err.println(usage);
      System.exit(1);
    }
    
    //Get the queries
    Map<Integer, String> queries = new HashMap<Integer, String>();
    scan = new Scanner(new File(params.get("queryFilePath")));
    line = null;
    do{
    	line = scan.nextLine();
    	String[] pairs = line.split(":");
    	queries.put(Integer.valueOf(pairs[0].trim()), pairs[1].trim());
    }while(scan.hasNext());
    
    for(Integer queryId : queries.keySet()){
    	String query = queries.get(queryId);
    	QryResult result = new QryResult();
    	
    	if(query.contains("And")){//with an operator
    		
    		//QueryParser parser = new QueryParser(matchVersion, f, a);
    		//		//(query,params.get("retrievalAlgorithm"));
    		//result = parser.parse().evaluate();
    	}else{//with no operator
    		String[] q = query.split(" ");
    		List<QryopTerm> qTerms = new ArrayList<QryopTerm>();
    		for(int i = 0; i < q.length; i++){
    			try {
					qTerms.add(new QryopTerm(tokenizeQuery(q[i])[0]));
				} catch (Exception e) {
					// TODO: handle exception
				}
    		}
    		if(params.get("retrievalAlgorithm").equals("UnrankedBoolean")){
    			 result = new QryopOr(qTerms.toArray(new QryopTerm[qTerms.size()])).evaluate();
    		}else if(params.get("retrievalAlgorithm").equals("RankedBoolean")){
    			result = new QryopOrFreq(qTerms.toArray(new QryopTerm[qTerms.size()])).evaluate();
    		}
    		
    	}
    	result.docScores.sortScores();
    	
    	printResults(queryId.toString(), result);
    	
    }  
