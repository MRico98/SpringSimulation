package Kernel.Model;

import Kernel.Controller.Event;

import java.util.HashMap;

public class VoteModel{
    private HashMap<String,Integer> votes = new HashMap<>();

    public void addvote(Event event){
        System.out.println("Aqui Estoy addvote");
        //int vote = getVotes().get(candidate);
        //vote++;
        //votes.replace(candidate,vote);
    }

    public void substractVote(Event event){
        System.out.println("Aqui Estoy substractVote");
        /*int vote = getVotes().get(candidate);
        if (validate(vote)){
            vote--;
            votes.replace(candidate,vote);
        }*/
    }

    //Aqui es donde deberiamos leer un archivo para que sea persistente los datos
    public void loadVoters(){
        HashMap<String,Integer> candidates = new HashMap<>();
        /*candidates.put("Candidato1",0);
        candidates.put("Candidato2",0);
        candidates.put("Candidato3",0);*/
        setVotes(candidates);
    }

    public void setVotes(HashMap<String, Integer> votes) {
        this.votes = votes;
    }

    public HashMap<String, Integer> getVotes() {
        return votes;
    }

    public boolean validate(Object object) {
        int votes = (int) object;
        return (votes>0);
    }
}
