package group1.go.Model;

/**
 * Created by giulianoscaglioni on 30/5/17.
 */
public class MinMaxTree {

    private static class StateNode {
        private State state;
        private ArrayList<StateNode> nextStates;

        public StateNode(State state) {
            this.state = state;
        }

    }

    private void generateNextStates(StateNode state, int depth)
    {
        if(depth>0)
        {

        }
    }

}
