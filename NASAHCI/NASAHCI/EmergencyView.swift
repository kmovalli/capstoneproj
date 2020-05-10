//
//  EmergencyView.swift
//  NASAHCI
//
//  Created by Sumedh Sohrab on 5/9/20.
//  Copyright © 2020 Sumedh Sohrab. All rights reserved.
//

import SwiftUI
import UIKit

var e1val = 0
var e2val = 0
var e3val = 0
var e4val = 0
var e5val = 0
var e6val = 0
var malert = false

struct EmergencyView: View {
    @State private var amessage = ""
    @State private var atitle = ""
    @State private var callAlert = false
    @EnvironmentObject var settings: UserSettings
    var body: some View {
        
        
            return Group {
                    
                    Group{
                        if malert {
                            Text("EMERGENCY!!!")
                            Spacer()
                            HStack(){
                                VStack() {
                                    Button("Power Off", action: onB1Press).buttonStyle(GradientBackgroundStyle())
                                    
                                    Button("Vent One", action: onB2Press).buttonStyle(GradientBackgroundStyle())

                                    
                                    Button("Vent Two", action: onB3Press).buttonStyle(GradientBackgroundStyle())
                                }
                                VStack() {
                                    Button("Vent Three", action: onB4Press).buttonStyle(GradientBackgroundStyle())

                                    
                                    Button("Vent Four", action: onB5Press).buttonStyle(GradientBackgroundStyle())

                                    
                                    Button("Open Fire Extinguisher", action: onB6Press).buttonStyle(GradientBackgroundStyle())
                                }
                            }
                            /*Button("Perform Actions", action: monitorInput).buttonStyle(GradientBackgroundStyle2()).padding(.vertical,20)*/
                            Spacer()
                            NavigationLink(destination: monitorInput()) {
                                     Text("Perform Actions")
                            }.buttonStyle(GradientBackgroundStyle2()).padding(.vertical,20)
                        } else {
                            NavigationLink(destination: submit()) {
                                     Text("Fix Emergency")
                            }.buttonStyle(GradientBackgroundStyle2()).padding(.vertical,20)
                            /*Button("Submit", action: submit).buttonStyle(GradientBackgroundStyle2()).padding(.vertical,20)*/
                        }
                    }
                }.navigationBarBackButtonHidden(true).navigationBarHidden(true)
        }
            func submit() -> AnyView{
                malert = true
                return monitorInput()
               
               
            }
            func onB1Press(){
                if e1val == 0{
                    e1val = 1
                } else if e2val == 0 {
                    e2val = 1
                } else if e3val == 0 {
                    e3val = 1
                } else if e4val == 0 {
                    e4val = 1
                } else if e5val == 0 {
                    e5val = 1
                } else if e6val == 0 {
                    e6val = 1
                } else {
                    monitorInput()
                }
                    
            }
            func onB2Press(){

                if e1val == 0{
                    e1val = 2
                } else if e2val == 0 {
                    e2val = 2
                } else if e3val == 0 {
                    e3val = 2
                } else if e4val == 0 {
                    e4val = 2
                } else if e5val == 0 {
                    e5val = 2
                } else if e6val == 0 {
                    e6val = 2
                } else {
                    monitorInput()
                }

            }
            func onB3Press(){
                
                if e1val == 0{
                    e1val = 3
                } else if e2val == 0 {
                    e2val = 3
                } else if e3val == 0 {
                    e3val = 3
                } else if e4val == 0 {
                    e4val = 3
                } else if e5val == 0 {
                    e5val = 3
                } else if e6val == 0 {
                    e6val = 3
                } else {
                    monitorInput()
                }

            }
            func onB4Press(){
                if e1val == 0{
                    e1val = 4
                } else if e2val == 0 {
                    e2val = 4
                } else if e3val == 0 {
                    e3val = 4
                } else if e4val == 0 {
                    e4val = 4
                } else if e5val == 0 {
                    e5val = 4
                } else if e6val == 0 {
                    e6val = 4
                } else {
                    monitorInput()
                }

            }
            func onB5Press(){
                if e1val == 0{
                    e1val = 5
                } else if e2val == 0 {
                    e2val = 5
                } else if e3val == 0 {
                    e3val = 5
                } else if e4val == 0 {
                    e4val = 5
                } else if e5val == 0 {
                    e5val = 5
                } else if e6val == 0 {
                    e6val = 5
                } else {
                    monitorInput()
                }

            }
            func onB6Press(){

                if e1val == 0{
                    e1val = 6
                } else if e2val == 0 {
                    e2val = 6
                } else if e3val == 0 {
                    e3val = 6
                } else if e4val == 0 {
                    e4val = 6
                } else if e5val == 0 {
                    e5val = 6
                } else if e6val == 0 {
                    e6val = 6
                } else {
                    monitorInput()
                }
 
            }
    
    func monitorInput() -> AnyView{
                //malert = true
                print(timeInterval)
                if timeInterval > 8000{
                    print("Do Nothing")
                } else {
                    
                    var exit = 0
                    
                    if e1val == 1 {
                        if e2val == 2 || e2val == 3 {
                            if e3val == 2 || e3val == 3 {
                                if e4val == 4 || e4val == 5 {
                                    if e5val == 4 || e5val == 5 {
                                        if e6val == 6 {
                                            exit = 1
                                        } else {
                                            exit = 2
                                        }
                                    } else {
                                        exit = 2
                                    }
                                } else {
                                    exit = 2
                                }
                            } else {
                                exit = 2
                            }
                        } else {
                            exit = 2
                        }
                    } else if e2val == 0 {
                        exit = 0
                    } else {
                        exit = 2
                    }
                    
                    if Int(exit) == 1 {
                        //changeValue(num: exit)
                        print("value \(exit)")
                        e1val = 0
                        e2val = 0
                        e3val = 0
                        e4val = 0
                        e5val = 0
                        e6val = 0
                        return AnyView(VStack(){
                        Text("The emergency was mitigated. \nYou can continue to with your tasks.")
                        NavigationLink(destination: AnyView(VStack(){
                        NavigationLink(destination: ContentView()){Text("Continue...")}
                        })){Text("Continue...")}
                        })
                    } else if exit == 2 {
                        //changeValue(num: exit)
                        print("value \(exit)")
                        //malert = 1
                        e1val = 0
                        e2val = 0
                        e3val = 0
                        e4val = 0
                        e5val = 0
                        e6val = 0
                        return AnyView(VStack(){
                            Text("You are off-nominal... System took over.")
                            NavigationLink(destination: AnyView(VStack(){
                            NavigationLink(destination: ContentView()){Text("Continue...")}
                            })){Text("Continue...")}
                            })
                    } else {
                        return AnyView(NavigationLink(destination: EmergencyView()){
                            Text("Continue!")
                            })
                    }
                }
            return AnyView(NavigationLink(destination: EmergencyView()){
            Text("Try Again!")
            })
            }
    }
struct GradientBackgroundStyle2: ButtonStyle {
 
    func makeBody(configuration: Self.Configuration) -> some View {
        configuration.label
            .frame(minWidth: 100, maxWidth: 200, minHeight: 10, maxHeight: 20)
            .padding(15)
            .foregroundColor(.white)
            .background(Color.blue)
            .cornerRadius(20)
            .padding(.horizontal, 10)
            .padding(.vertical,10)
            .shadow(color: Color.blue, radius: 10)
            .scaleEffect(configuration.isPressed ? 0.9 : 1.0)
    }
}

struct EmergencyView_Previews: PreviewProvider {
    static var previews: some View {
        EmergencyView()
    }
}
