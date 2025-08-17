/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

import javax.print.attribute.SetOfIntegerSyntax;


/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
 

interface GroupMeetings{

  public List<Meeting> mergeMeetings(List<Meeting> meetings);

}

class GroupConsecutiveMeetings implements GroupMeetings{

  @Override
  public List<Meeting> mergeMeetings(List<Meeting> meetings){
    if (meetings.size() == 0){
      return Collections.emptyList();
    }
    Comparator<Meeting> comp = (
      (a,b) -> {
          if (a.getStartTime() == b.getStartTime()){
            return a.getEndTime() - b.getEndTime();
          }
          return a.getStartTime() - b.getStartTime();
      }
    );
    Collections.sort(meetings, comp);


    List<Meeting> mergedMeetings = new ArrayList<>();
    mergedMeetings.add(meetings.get(0)); //first meeting
    int previousMeetingCounter = 0;

//[1, 10, Alice, Bob]
//[10,20, Alice, Kevin]

//mergedMeetings = [[1, 20, Alice, Bob],[10,20, Alice, Kevin]]

    for (int i = 1; i < meetings.size(); i++){
      //if start time of current interval = end of previous + 1; 
      Meeting currentMeeting = meetings.get(i);
      System.out.println("previousMeeting" + previousMeetingCounter);
      Meeting previousMeeting = mergedMeetings.get(mergedMeetings.size() - 1);

      
      Set<String> prevNames = previousMeeting.getNames();
      Set<String> currNames = currentMeeting.getNames();
      boolean hasShared = false;
      for (String name : currNames) {
          if (prevNames.contains(name)) {
              hasShared = true;
              break;
          }
      }

      if (currentMeeting.getStartTime() == previousMeeting.getEndTime() + 1 && hasShared){
           //merge - min(start times) & max (end times)
          int newStartTime = Math.min(currentMeeting.getStartTime() , previousMeeting.getStartTime());
          int newEndTime = Math.max(currentMeeting.getEndTime(), previousMeeting.getEndTime());
          previousMeeting.setStartTime(newStartTime);
          previousMeeting.setEndTime(newEndTime);
        
        //add new participantNames from current interval to previous 
          Set<String> previousMeetingNames = previousMeeting.getNames(); //Alice, Bob
          Set<String> currentMeetingNames = currentMeeting.getNames(); //Alice, Kevin
          for (String name : currentMeetingNames){
            if (!previousMeetingNames.contains(name)){
              previousMeetingNames.add(name);
            }
          }
      } else{
        mergedMeetings.add(currentMeeting);
      }
           
      //else
        //add current interval as it is to mergedMeetings list
      previousMeetingCounter++;
    }

    return mergedMeetings;

  }
}

class Meeting{
  int start;
  int end;
  Set<String> participantNames;

  //constructors
  //setters and getters

  public Meeting(int start, int end, Set<String> names){
    this.start = start;
    this.end = end;
    this.participantNames = names;
  }
  public int getStartTime(){
    return start;
  }

  public int getEndTime(){
    return end;
  }

   public Set<String> getNames(){
    return participantNames;
  }

  public void setStartTime(int start){
    this.start = start;
  }

 public void setEndTime(int end){
    this.end = end;
  }
  public void setNames(Set<String> names){
    this.participantNames = names;
  }
  

}
class MeetingManager {
  GroupMeetings groupConsecutiveMeetings;
  List<Meeting> meetings;

  public MeetingManager(){
    groupConsecutiveMeetings = new GroupConsecutiveMeetings();
  }
  
  public List<Meeting> mergeMeetings(List<Meeting> meetings){
    return groupConsecutiveMeetings.mergeMeetings(meetings);
  }

}

class Solution {
  public static void main(String[] args) throws Exception {
    MeetingManager meetingManager = new MeetingManager();
    List<Meeting> meetings = new ArrayList<>();

    Set<String> participants1 = new HashSet<>();
    participants1.add("Alice");
    participants1.add("Bob");
    Meeting meeting1 = new Meeting(1, 10, participants1);

    Set<String> participants2 = new HashSet<>();
    participants2.add("Alice");
    participants2.add("Kevin");
    Meeting meeting2 = new Meeting(11, 20, participants2);

    Set<String> participants3 = new HashSet<>();
    participants3.add("John");
    participants3.add("Kevin");
    Meeting meeting3 = new Meeting(21, 30, participants3);

    meetings.add(meeting1);
    meetings.add(meeting2);
    meetings.add(meeting3);

    List<Meeting> meetings1 = meetingManager.mergeMeetings(meetings);
    for (Meeting meeting : meetings1){
      System.out.println("Meeting:" + meeting.getStartTime() + meeting.getEndTime()+ meeting.getNames());
    }
  }

}


//Group meetings by shared participants and consecutive time blocks

//class Meeting - start , end, List<String> participants
//input - List<Meetings>

//[1, 10, Alice, Bob]
//[11,20, Alice, Kevin]

//[1, 20, Alice, Bob, Kevin]

//consecutive time blocks, merged their intervals
//merged participants

//interface GroupMeetings 