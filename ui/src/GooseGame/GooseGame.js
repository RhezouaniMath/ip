import React from "react";

const array = [];

for (let i = 0; i < 64; i++) {
    if (i==0){
        array[i] = "START";
    }
    else{ 
        let number = i;
        let text = number.toString();
        array[i] = text;
    }
}

export function GooseGame() {
    return <div>
        <h2>Game of the Goose</h2>
        <div>
            <div>
                UNDER CONSTRUCTION
            </div>
            <div>
                {array.map( 
                    (i) => 
                    (   <div id = {i} >
                            {i}
                        </div>)
                    )
                }
            </div>
        </div>
    </div>
}