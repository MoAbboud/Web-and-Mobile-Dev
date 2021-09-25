import {Component, OnInit, OnDestroy} from '@angular/core';
import {Subscription, interval} from 'rxjs';

@Component({
  selector: 'app-timer',
  templateUrl: './Timer.component.html',
  styleUrls: ['./Timer.component.css']
})

export class TimerComponent implements OnInit, OnDestroy {

  private subscription: Subscription | undefined;
  public date = new Date(); //Get current date time
  milliSecondsInASecond = 1000; //Declare the milliseconds in a second
  hoursInADay = 24; //Declare teh hours in a day
  minutesInAnHour = 60; //Declare the minutes in an hour
  SecondsInAMinute = 60; // Declare the seconds in a minute

  //Initialize the variables that will be used to display the values
  public timeDifference = 0;
  public secondsToDday = 0;
  public minutesToDday = 0;
  public hoursToDday = 0;
  public daysToDday = 0;

  public getTimeDifference() {
    //Subtract the date inputed by the user and the current date to get the total milliseconds.
    this.timeDifference = this.date.getTime() - new Date().getTime();
    //As long as the milliseconds is greater than 0, call the allocateTimeUnits function.
    if(this.date.getTime() - new Date().getTime() > 0){
      this.allocateTimeUnits(this.timeDifference);
    }
  }

  private allocateTimeUnits(timeDifference: number) {
    //Convert the milliseconds to seconds
    this.secondsToDday = Math.floor((timeDifference) /
      (this.milliSecondsInASecond) % this.SecondsInAMinute);
    //Convert the milliseconds to minutes
    this.minutesToDday = Math.floor((timeDifference) /
      (this.milliSecondsInASecond * this.minutesInAnHour) % this.SecondsInAMinute);
    //Convert the milliseconds to hours
    this.hoursToDday = Math.floor((timeDifference) /
      (this.milliSecondsInASecond * this.minutesInAnHour * this.SecondsInAMinute)
      % this.hoursInADay);
    //Convert the milliseconds to days
    this.daysToDday = Math.floor((timeDifference) /
      (this.milliSecondsInASecond * this.minutesInAnHour * this.SecondsInAMinute
        * this.hoursInADay));
  }

  getTime(event: any) {
    //Get the date that was inputed by the user.
    this.date = new Date((<HTMLInputElement>event.target).value);
  }

  ngOnInit() {
    //Give the subscription an interval and add the function that will be used to
    //constantly run.
    this.subscription = interval(1000)
      .subscribe(x => {
        this.getTimeDifference();
      });
  }

  ngOnDestroy() {
    // @ts-ignore
    //Stop the interval function and halt the repeated calling of the function above.
    this.subscription.unsubscribe();
  }

}
