import React from "react";
import Nav from "react-bootstrap/Nav";
import Button from "react-bootstrap/Button";

class Sidenav extends React.Component {
    render() {
        let objectsList = this.props.objects.map((object) => {
            return <Nav.Link href={"#"} key={object.key}><Button variant={"outline-dark"} block>{object.displayName}</Button></Nav.Link>
        })

        return (
            <Nav fill activeKey={"#"} className={"flex-column mt-3"}>
                <Nav.Link href={"#"}><Button variant={"outline-dark"}
                                             block>Rekrutacje</Button></Nav.Link>
                {objectsList}
                {/*<Nav.Link href={"#"}><Button variant={"outline-dark"} block>Wydziały</Button></Nav.Link>*/}
            </Nav>
        );
    }
}

export default Sidenav;