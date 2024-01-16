import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import {ThemeProvider, useTheme} from "@mui/material/styles";


function SuccessfullyOrdered() {
    const theme = useTheme();

    const customStyles = {
        backgroundImage: 'url("https://images.unsplash.com/photo-1491146179969-d674118945ff?q=80&w=2832&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")',
        backgroundSize: 'cover',
        backgroundRepeat: 'no-repeat',
        backgroundPosition: 'center',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        textAlign: 'center',
        height: '100vh',
        overflow: 'hidden',
    };

    return (
        <ThemeProvider theme={theme}>
            <div
                style={customStyles}
            >
                <Container maxWidth="sm">
                    <Typography variant="h4" gutterBottom
                                style={{fontSize: '45px', fontWeight: 'bold', color: 'ghostwhite'}}>
                        Successfully Ordered
                    </Typography>
                    <Typography variant="h4" gutterBottom
                                style={{fontSize: '25px', fontWeight: 'bold', color: 'ghostwhite'}}>
                        Thank you! Your order has been successfully placed.
                    </Typography>
                </Container>
            </div>
        </ThemeProvider>
    )
}

export default SuccessfullyOrdered;