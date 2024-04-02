package parser;

import aima.ActionsFunction;
import aima.DynamicAction;
import aima.GoalTest;
import aima.ResultFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aima.Action;

public class NodeFunctionGenerator {
	private  ActionsFunction _ActionsFunction = null;
	private  ResultFunction _ResultFunction = null;
	private  GoalTest _GoalFunction = null;
	private Arc relations;
	String goal;
	
	public NodeFunctionGenerator(String goal, Arc relations){
		this.goal = goal;
		this.relations = relations;
	}
	
	public ActionsFunction getActionfunction() {
		if (null == _ActionsFunction) {
			_ActionsFunction = new getStops(relations);
		}
		return _ActionsFunction;
	}
	
	public ResultFunction getResultFunction() {
		if (null == _ResultFunction) {
			_ResultFunction = new getResults(relations);
		}
		return _ResultFunction;
	}
	
	public GoalTest getGoalFunction() {
		if (null == _GoalFunction) {
			_GoalFunction = new getGoal(goal);
		}
		return _GoalFunction;
	}
	
	private class getStops implements ActionsFunction {
		private Arc relations;
		getStops(Arc relations){
			this.relations = relations;
		}
		
		public Set<Action> actions(Object s) {
			String stop = (String) s;
			Set<Action> neighbors = new HashSet<>();
			List<String> actions = relations.getNeighbors(stop);
			for(String action: actions) {
				neighbors.add(new DynamicAction(action));
			}
			return neighbors;
			
		}
		
	}
	
	private class getResults implements ResultFunction {
		private Arc relations;
		getResults(Arc relations){
			this.relations = relations;
		}
		public Object result(Object s, Action a) {
			if(a instanceof DynamicAction) {
				DynamicAction move = (DynamicAction) a;
				String stop = (String) s;
				List<String> actions = relations.getNeighbors(stop);
				//could maybe remove and just make it return move.getName()
				for(String action: actions) {
					if(action.equals(move.getName())) {
						return action;
					}
				}
			}
			return s;
		}
		
	}
	
	private class getGoal implements GoalTest {
		//since it's not static, it allows you to pass the state argument through this function
		private String goal;
		getGoal(String goal){
			this.goal = goal;
		}
	
	public boolean isGoalState(Object state) {
			String current = (String) state;
			if(current.equals(goal)) {
				return true;
			}
			return false;
		}
		
	}

}
