package chat.model;

public interface Interactable
{
	public String[] findRelatedTopics(String topic);
	public String[] findRelatedTopics(String[] topics);
	public void turnStatusesToWords();
	public void turnStatusesToFormatedWords();
	public void sendMessage(String message);
}
