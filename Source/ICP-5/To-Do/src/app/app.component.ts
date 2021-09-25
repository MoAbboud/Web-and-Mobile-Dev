import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  public items = [];

  public newTask: string | undefined;

  title: string | undefined;
  public addToList() {
    if (this.newTask == '') {

    }
    else
    {
      // @ts-ignore
      this.items.push(this.newTask);
      this.newTask = '';
    }
  }

  public deleteTask(index: number) {
    this.items.splice(index, 1);
  }
}
