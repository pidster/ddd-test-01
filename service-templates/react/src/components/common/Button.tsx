import React from 'react';
import { Button as MuiButton, ButtonProps as MuiButtonProps, styled } from '@mui/material';

// Extend the MUI button props to add custom props
export interface ButtonProps extends MuiButtonProps {
  variant?: 'primary' | 'secondary' | 'success' | 'error' | 'warning' | 'info' | 'text';
  fullWidth?: boolean;
  size?: 'small' | 'medium' | 'large';
}

// Map our custom variants to MUI variants and colors
const getVariantMapping = (variant: ButtonProps['variant']): {
  muiVariant: MuiButtonProps['variant'];
  color: MuiButtonProps['color'];
} => {
  switch (variant) {
    case 'primary':
      return { muiVariant: 'contained', color: 'primary' };
    case 'secondary':
      return { muiVariant: 'contained', color: 'secondary' };
    case 'success':
      return { muiVariant: 'contained', color: 'success' };
    case 'error':
      return { muiVariant: 'contained', color: 'error' };
    case 'warning':
      return { muiVariant: 'contained', color: 'warning' };
    case 'info':
      return { muiVariant: 'contained', color: 'info' };
    case 'text':
      return { muiVariant: 'text', color: 'primary' };
    default:
      return { muiVariant: 'contained', color: 'primary' };
  }
};

// Styled MUI button with additional styling
const StyledButton = styled(MuiButton)(({ theme }) => ({
  borderRadius: '4px',
  boxShadow: 'none',
  fontWeight: 600,
  textTransform: 'none',
  '&:hover': {
    boxShadow: 'none',
  },
}));

export const Button: React.FC<ButtonProps> = ({
  variant = 'primary',
  size = 'medium',
  fullWidth = false,
  disabled = false,
  children,
  ...restProps
}) => {
  const { muiVariant, color } = getVariantMapping(variant);

  return (
    <StyledButton
      variant={muiVariant}
      color={color}
      size={size}
      fullWidth={fullWidth}
      disabled={disabled}
      {...restProps}
    >
      {children}
    </StyledButton>
  );
};

export default Button; 