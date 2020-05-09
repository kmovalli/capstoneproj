func emerInput() {
            let stop = DispatchTime.now()
            let nanoTime = stop.uptimeNanoseconds - start.uptimeNanoseconds
            let time = Double(nanoTime)/1000
            timeInterval = time
            //print(timeInterval)

            if timeInterval > 6000{
                if count > 0{
                    message = "System is taking over..."
                    showingError = false
                    count = 0
                    //ContentView()
                } else {
                    message = "Try again"
                    count += 1
                }
            }else{
                if (e1val == 1 && (e2val == 2 || e2val == 3) && (e3val == 2 || e3val == 3) && (e4val == 4 || e4val == 5) && (e5val == 4 || e5val == 5) && e6val == 6) {
                    showingError = false;
                } else {
                    if count > 0{
                        message = "System is taking over..."
                        showingError = false
                        count = 0
                        //ContentView()
                    } else {
                        message = "Try again"
                        count += 1
                    }
                }
            }

            e1val = 0
            e2val = 0
            e3val = 0
            e4val = 0
            e5val = 0
            e6val = 0
            timeInterval = 0.0
            nomVal = 0.0

        }
