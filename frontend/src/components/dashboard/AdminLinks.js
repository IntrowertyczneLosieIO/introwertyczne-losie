import React from "react";
import Button from "react-bootstrap/Button";
import Nav from "react-bootstrap/Nav";
import {Link} from "react-router-dom";

class AdminLinks extends React.Component {
    render() {
        return (
            // <ul className={"list-inline"}>
            //     <li className={"list-inline-item"}>
            //         <div className={"mr-5"}>
            //             <Link to={"/"}><Button variant="outline-light">Ustawienia konta</Button></Link>
            //         </div>
            //     </li>
            //     <li className={"list-inline-item"}>
            //         <div className={"mr-5"}>
            //             <Link to={"/"}><Button variant="outline-light">Pomoc</Button></Link>
            //         </div>
            //     </li>
            //     <li className={"list-inline-item"}>
            //         <div className={"ml-5"}>
            //<Link to={"/"}><Button variant="outline-light">Wyloguj się</Button></Link>
            //         </div>
            //     </li>
            // </ul>
            <Nav>
                <div className={"mr-5"}>
                    <Button variant="outline-light">Ustawienia konta</Button>
                </div>
                <div className={"mr-5"}>
                    <Button variant="outline-light">Pomoc</Button>
                </div>
                <div className={"ml-5"}>
                    <Link to={"/logout"}><Button variant="outline-light">Wyloguj się</Button></Link>
                </div>
            </Nav>
        );
    }
}

export default AdminLinks;