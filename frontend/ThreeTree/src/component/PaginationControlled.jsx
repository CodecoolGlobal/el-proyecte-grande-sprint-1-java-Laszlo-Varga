import PropTypes from 'prop-types';
import Typography from '@mui/material/Typography';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';

export default function PaginationControlled({ totalProducts, pageSize, page, onChange }) {

    PaginationControlled.propTypes = {
        totalProducts: PropTypes.number.isRequired,
        pageSize: PropTypes.number.isRequired,
        page: PropTypes.number.isRequired,
        onChange: PropTypes.func.isRequired,
    };

    return (
        <Stack spacing={2} sx={{ marginTop: '2em' }}>
            <Typography fontSize={18}>Page: {page}</Typography>
            <Pagination
                count={Math.ceil(totalProducts / pageSize)}
                page={page}
                onChange={onChange}
                size="large"
            />
        </Stack>
    );
}
