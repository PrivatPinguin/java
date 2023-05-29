export class Clock {
  constructor(hours=0, minutes=0) {
    this.hours= hours;
    this.minutes= minutes;
  }

  toString() {
    let time = '';
    this.normTime() //get normalized time 
    
    if(this.hours < 10){
      time = '0' + this.hours;
    }else{
      time = this.hours;
    }
    if(this.minutes < 10){
      time += ':0' + this.minutes;
    }else{
      time += ':' + this.minutes;
    }
    return time;
  }

  plus(minutes) {
    this.minutes += minutes;
    return this.toString();
  }

  minus(minutes) {
    this.minutes -= minutes;
    return this.toString();
  }

  equals(otherClock) {
    return (this.toString() == otherClock.toString()) //checks normalized strings if equal
  }

  normTime(){
    while(this.minutes >= 60){
      this.minutes -= 60;
      this.hours += 1;
    }
    if(this.hours >= 24){
      this.hours = this.hours % 24;
    }
    while(this.minutes < 0){
      this.minutes = 60 + this.minutes;
      this.hours -=1;
    }
    while(this.hours < 0){
      this.hours = 24 + this.hours;
    }
  }
}
