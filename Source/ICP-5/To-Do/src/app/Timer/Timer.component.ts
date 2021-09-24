import {Component, OnInit, OnDestroy} from '@angular/core';
import {Subscription, interval} from 'rxjs';

@Component({
  selector: 'app-timer',
  templateUrl: './Timer.component.html',
  styleUrls: ['./Timer.component.css']
})

export class TimerComponent implements OnInit, OnDestroy {

  private subscription: Subscription | undefined;
  public date = new Date();
  milliSecondsInASecond = 1000;
  hoursInADay = 24;
  minutesInAnHour = 60;
  SecondsInAMinute = 60;

  public timeDifference = 0;
  public secondsToDday = 0;
  public minutesToDday = 0;
  public hoursToDday = 0;
  public daysToDday = 0;

  public getTimeDifference() {
    this.timeDifference = this.date.getTime() - new Date().getTime();

    if(this.date.getTime() - new Date().getTime() > 0){
      this.allocateTimeUnits(this.timeDifference);
    }
  }

  private allocateTimeUnits(timeDifference: number) {
    this.secondsToDday = Math.floor((timeDifference) /
      (this.milliSecondsInASecond) % this.SecondsInAMinute);
    this.minutesToDday = Math.floor((timeDifference) /
      (this.milliSecondsInASecond * this.minutesInAnHour) % this.SecondsInAMinute);
    this.hoursToDday = Math.floor((timeDifference) /
      (this.milliSecondsInASecond * this.minutesInAnHour * this.SecondsInAMinute)
      % this.hoursInADay);
    this.daysToDday = Math.floor((timeDifference) /
      (this.milliSecondsInASecond * this.minutesInAnHour * this.SecondsInAMinute
        * this.hoursInADay));
  }

  getTime(event: any) {
    this.date = new Date((<HTMLInputElement>event.target).value);
  }

  ngOnInit() {
    this.subscription = interval(1000)
      .subscribe(x => {
        this.getTimeDifference();
      });
  }

  ngOnDestroy() {
    // @ts-ignore
    this.subscription.unsubscribe();
  }

}
